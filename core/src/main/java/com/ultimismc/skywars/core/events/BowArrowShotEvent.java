package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.entity.Arrow;

/**
 * @author DirectPlan
 */
@Getter
public class BowArrowShotEvent extends UserSkyWarsEvent {

    private final Arrow arrow;

    public BowArrowShotEvent(User user, Arrow arrow) {
        super(user);

        this.arrow = arrow;
    }
}
