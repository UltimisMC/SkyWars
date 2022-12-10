package xyz.directplan.directlib.combat;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * @author DirectPlan
 */
public enum AttackCause {

    KILL,
    VOID,
    FALL_DAMAGE,
    PROJECTILE,
    BOW;

    public static AttackCause translate(EntityDamageEvent.DamageCause damageCause, Projectile projectile) {
        switch (damageCause) {
            case VOID: {
                return VOID;
            }
            case FALL: {
                return FALL_DAMAGE;
            }
            case PROJECTILE: {
                if(projectile instanceof Arrow) {
                    return BOW;
                }
                return PROJECTILE;
            }
            default: {
                return KILL;
            }
        }
    }

    public boolean isEntityAttack() {
        return this == KILL;
    }

    public boolean isFallDamage() {
        return this == FALL_DAMAGE;
    }

    public boolean isVoid() {
        return this == VOID;
    }

    public boolean isBow() {
        return this == BOW;
    }
    public boolean isProjectile() {
        return isBow() || this == PROJECTILE;
    }

}
