package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractUserEvent extends AbstractEvent {

    private final User user;

    public AbstractUserEvent(GameServer gameServer, User user) {
        super(gameServer);
        this.user = user;
    }
}
