package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public class GameStartedEvent extends UserSkyWarsEvent {

    public GameStartedEvent(User user) {
        super(user);
    }
}
