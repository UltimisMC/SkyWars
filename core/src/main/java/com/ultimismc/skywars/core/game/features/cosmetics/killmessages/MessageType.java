package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import xyz.directplan.directlib.combat.AttackCause;

/**
 * @author DirectPlan
 */
public enum MessageType {

    SCREEN,
    KILL,
    VOID,
    FALL_DAMAGE,
    BOW;

    public static MessageType translate(AttackCause attackCause) {
        switch (attackCause) {
            case VOID: {
                return VOID;
            }
            case FALL_DAMAGE: {
                return FALL_DAMAGE;
            }
            case PROJECTILE:
            case BOW: {
                return BOW;
            }
            default: {
                return KILL;
            }
        }
    }
}
