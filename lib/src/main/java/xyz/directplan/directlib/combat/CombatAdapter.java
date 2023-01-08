package xyz.directplan.directlib.combat;

import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author DirectPlan
 */
public interface CombatAdapter<U> {

    /**
     * Gets called with {@param player} was attacked
     *
     * @param user The player who got attacked
     * @param attacker The attacker
     * @param projectile the projectile used for this attack
     * @param attackCause What caused this attack.
     * @return Whether this attack should be allowed
     */
    boolean onAttack(U user, U attacker, Projectile projectile, AttackCause attackCause);

    /**
     * Gets call when {@param user} dies.
     *
     * @param user The victim
     * @param killer The killer
     * @param attackCause What caused this death.
     */
    void onDeath(U user, U killer, List<ItemStack> drops, AttackCause attackCause);

    /**
     * Gets called for all players that assisted in killing {@param user}
     *
     * @param user The victim.
     * @param assistant The assistant who assisted in killing {@param user}
     * @param attackCause What caused this assist.
     */
    void onAssist(U user, U assistant, AttackCause attackCause);
}
