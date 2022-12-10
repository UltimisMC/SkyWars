package xyz.directplan.directlib.combat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author DirectPlan
 */
public class CombatListener implements Listener {

    private final CombatManager<?> combatManager;

    public CombatListener(CombatManager<?> combatManager) {
        this.combatManager = combatManager;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        combatManager.onDamage(event);
    }

    @EventHandler
    public void onCombatQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        EntityDamageEvent fakeEvent = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.ENTITY_ATTACK, 100.0);
        combatManager.onDamage(fakeEvent);
    }
}
