package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public abstract class UserSkyWarsEvent extends SkyWarsEvent {

    private final User user;

    public UserSkyWarsEvent(GameConfig gameConfig, User user) {
        super(gameConfig);
        this.user = user;
    }
}
