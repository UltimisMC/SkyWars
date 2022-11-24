package xyz.directplan.directlib.combat;

import lombok.Getter;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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
        if(event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent damageByEntityEvent = (EntityDamageByEntityEvent) event;
            Entity damager = damageByEntityEvent.getDamager();
            if(damager instanceof Arrow) {
                Arrow arrow = (Arrow) damager;
                damager = (Entity) arrow.getShooter();
            }
            if(damager instanceof Player) {
                attacker = (Player) damager;
            }
        }
        if(combatAdapter.onAttack(player, attacker)) return;

        EntityDamageEvent.DamageCause damageCause = event.getCause();
        UUID playerUuid = player.getUniqueId();

        U user = getUser(player);

        addAttacker(playerUuid, attacker);

        double finalDamage = event.getFinalDamage();
        if(player.getHealth() - finalDamage > 0) {
            // Player is attacked but not enough to kill him.
            return;
        }

        // Player is dead
        U killer = getUser(attacker);
        if(killer == null) {
            Player playerKiller = pollLastAttacker(playerUuid);
            killer = getUser(playerKiller);
        }

        boolean cancelled = combatAdapter.onDeath(user, killer, damageCause);
        if(cancelled) {
            event.setCancelled(true);
            return;
        }
        // Execute assists
        LinkedList<Combat> assists = combatMap.get(playerUuid);
        for(Combat combat : assists) {
            U assistUser = getUser(combat.getPlayer());
            combatAdapter.onAssist(user, assistUser, damageCause);
        }
        combatMap.remove(playerUuid);
    }

    public void addAttacker(UUID attacked, Player attacker) {
        LinkedList<Combat> assists = combatMap.computeIfAbsent(attacked, uuid -> new LinkedList<>());
        assists.addLast(new Combat(attacker));
    }

    public Player pollLastAttacker(UUID attacked) {
        LinkedList<Combat> assists = combatMap.get(attacked);
        if(assists == null || assists.isEmpty()) return null;
        return assists.pollLast().getPlayer();
    }

    public abstract U getUser(Player player);
}
