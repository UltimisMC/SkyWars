package xyz.directplan.directlib.combat;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * @author DirectPlan
 */
public interface CombatAdapter<U> {

    /**
     * Gets called with {@param player} was attacked
     *
     * @param player The player who got attacked
     * @param attacker The attacker
     * @return Whether this attack should be allowed
     */
    boolean onAttack(Player player, Player attacker);

    /**
     * Gets call when {@param user} dies.
     *
     * @param user The victim
     * @param killer The killer
     * @param damageCause What caused this death.
     */
    void onDeath(U user, U killer, EntityDamageEvent.DamageCause damageCause);

    /**
     * Gets called for all players that assisted in killing {@param user}
     *
     * @param user The victim.
     * @param assistant The assistant who assisted in killing {@param user}
     * @param damageCause What caused this assist.
     */
    void onAssist(U user, U assistant, EntityDamageEvent.DamageCause damageCause);
}
