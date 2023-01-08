package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class UserKillEvent extends UserSkyWarsEvent {

    private final User victim;
    private final boolean voidKill;

    public UserKillEvent(GameConfig gameConfig, User user, User victim, boolean voidKill) {
        super(gameConfig, user);

        this.victim = victim;
        this.voidKill = voidKill;
    }
}
