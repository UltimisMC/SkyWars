package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class UserDeathEvent extends UserSkyWarsEvent {

    private final User killer;

    public UserDeathEvent(GameConfig gameConfig, User user, User killer) {
        super(gameConfig, user);

        this.killer = killer;
    }

    public UserDeathEvent(GameConfig gameConfig, User user) {
        this(gameConfig, user, null);
    }
}
