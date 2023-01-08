package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import xyz.directplan.directlib.combat.AttackCause;

/**
 * @author DirectPlan
 */
@Getter
public class UserKillEvent extends UserSkyWarsEvent {

    private final User victim;
    private final AttackCause attackCause;

    public UserKillEvent(User user, User victim, AttackCause attackCause) {
        super(user);

        this.victim = victim;
        this.attackCause = attackCause;
    }

    public boolean isVoidKill() {
        return attackCause.isVoid();
    }
}
