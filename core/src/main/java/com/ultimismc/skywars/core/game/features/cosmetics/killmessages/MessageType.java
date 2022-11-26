package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import org.bukkit.event.entity.EntityDamageEvent;

/**
 * @author DirectPlan
 */
public enum MessageType {

    SCREEN,
    KILL,
    VOID,
    FALL_DAMAGE,
    BOW;

    public static MessageType translate(EntityDamageEvent.DamageCause damageCause) {
        switch (damageCause) {
            case VOID: {
                return VOID;
            }
            case FALL: {
                return FALL_DAMAGE;
            }
            case PROJECTILE: {
                return BOW;
            }
            default: {
                return KILL;
            }
        }
    }
}
