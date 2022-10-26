package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class UserDeathEvent extends AbstractUserEvent {

    private final User killer;

    public UserDeathEvent(GameServer gameServer, User user, User killer) {
        super(gameServer, user);

        this.killer = killer;
    }

    public UserDeathEvent(GameServer gameServer, User user) {
        this(gameServer, user, null);
    }
}
