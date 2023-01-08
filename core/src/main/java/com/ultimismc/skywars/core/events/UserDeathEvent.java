package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class UserDeathEvent extends UserSkyWarsEvent {

    private final User killer;

    public UserDeathEvent(User user, User killer) {
        super(user);

        this.killer = killer;
    }

    public UserDeathEvent(User user) {
        this(user, null);
    }
}
