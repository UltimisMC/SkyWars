package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractUserEvent extends AbstractEvent {

    private final User user;

    public AbstractUserEvent(GameConfig gameConfig, User user) {
        super(gameConfig);
        this.user = user;
    }
}
