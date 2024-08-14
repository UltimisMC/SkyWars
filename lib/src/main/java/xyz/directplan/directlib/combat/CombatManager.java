package xyz.directplan.directlib.combat;

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
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;

/**
 * @author DirectPlan
 */
@Getter
public abstract class CombatManager<U> {

    private final JavaPlugin plugin;

    private final CombatAdapter<U> combatAdapter;

    private final Map<UUID, LinkedList<Combat>> combatMap = new HashMap<>();

    public CombatManager(JavaPlugin plugin, CombatAdapter<U> combatAdapter) {
        this.plugin = plugin;
        this.combatAdapter = combatAdapter;
    }

    public void startCombatManager() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new CombatListener(this), plugin);

        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.runTaskTimer(plugin, new CombatRunnable(this), 20L, 20L);
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

        if(attacker != null) addAttacker(playerUuid, attacker);

        double finalDamage = event.getFinalDamage();
        if(attackCause.isVoid()) finalDamage = 40.0;

        if(player.getHealth() - finalDamage > 0) {
            // Insufficient damage for death
            return;
        }

        // Player is dead
        if(attackerUser == null) {
            attacker = pollLastAttacker(playerUuid);
            if(attacker != null) {
                attackerUser = getUser(attacker);
            }
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

        LinkedList<Combat> assists = combatMap.remove(playerUuid);
        if(assists == null) return;
        for(Combat combat : assists) {
            Player assistant = combat.getPlayer();
            if(assistant == attacker) continue;
            U assistUser = getUser(combat.getPlayer());
            combatAdapter.onAssist(user, assistUser, attackCause);
        }
    }

    public void addAttacker(UUID attacked, Player attacker) {
        LinkedList<Combat> assists = combatMap.computeIfAbsent(attacked, uuid -> new LinkedList<>());
        Combat combat = new Combat(attacker);
        if(assists.contains(combat)) return;
        assists.addLast(combat);
    }

    public Player pollLastAttacker(UUID attacked) {
        LinkedList<Combat> assists = combatMap.get(attacked);
        if(assists == null || assists.isEmpty()) return null;
        return assists.pollLast().getPlayer();
    }

    public abstract U getUser(Player player);
}
