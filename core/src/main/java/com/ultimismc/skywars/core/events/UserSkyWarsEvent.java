package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public abstract class UserSkyWarsEvent extends SkyWarsEvent {

    private final User user;

    public UserSkyWarsEvent(User user) {
        this.user = user;
    }
}
