package xyz.directplan.directlib.combat;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.util.*;

/**
 * @author DirectPlan
 */
@Getter
public abstract class CombatManager<U> {

    private final JavaPlugin plugin;
    private final CombatAdapter<U> combatAdapter;
    private final LoadingCache<UUID, Combat> combatCache;

    public CombatManager(JavaPlugin plugin, CombatAdapter<U> combatAdapter, Duration combatDuration) {
        this.plugin = plugin;
        this.combatAdapter = combatAdapter;

        combatCache = Caffeine.newBuilder()
                .expireAfterWrite(combatDuration)
                .build(key -> null);
    }

    public void startCombatManager() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new CombatListener(this), plugin);
    }

    public void onDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        Player attacker = null;
        Projectile projectile = null;
        if(event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent damageByEntityEvent = (EntityDamageByEntityEvent) event;
            Entity damager = damageByEntityEvent.getDamager();
            if(damager instanceof Projectile) {
                projectile = (Projectile) damager;
                damager = (Entity) projectile.getShooter();
            }
            if(damager instanceof Player) {
                attacker = (Player) damager;
            }
        }
        EntityDamageEvent.DamageCause damageCause = event.getCause();
        AttackCause attackCause = AttackCause.translate(damageCause, projectile);
        U user = getUser(player);
        U attackerUser = (attacker != null ? getUser(attacker) : null);
        if(combatAdapter.onAttack(user, attackerUser, projectile, attackCause)) {
            event.setCancelled(true);
            return;
        }

        UUID playerUuid = player.getUniqueId();

        if(attacker != null) addCombat(playerUuid, attacker);

        double finalDamage = event.getFinalDamage();
        if(attackCause.isVoid()) finalDamage = 40.0;

        if(player.getHealth() - finalDamage > 0) {
            // Insufficient damage for death
            return;
        }

        // Player is dead
        if(attackerUser == null) {
            attacker = getLastAttacker(playerUuid);
            if(attacker != null) attackerUser = getUser(attacker);
        }
        List<ItemStack> drops = new ArrayList<>();
        for(ItemStack content : player.getInventory()) {
            if(content == null || content.getType() == Material.AIR) continue;
            drops.add(content);
        }
        Location location = player.getLocation();
        combatAdapter.onDeath(user, attackerUser, drops, attackCause);
        for(ItemStack item : drops) {
            World world = player.getWorld();
            world.dropItem(location, item);
        }
        event.setCancelled(true);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setFireTicks(0);
    }

    public void addCombat(UUID uuid, Player attacker) {
        combatCache.put(uuid, new Combat(attacker));
    }

    public Player getLastAttacker(UUID victimUuid) {
        Combat combat = combatCache.get(victimUuid);

        return Optional.ofNullable(combat)
                .map(Combat::getPlayer)
                .orElse(null);
    }

    public abstract U getUser(Player player);
}
