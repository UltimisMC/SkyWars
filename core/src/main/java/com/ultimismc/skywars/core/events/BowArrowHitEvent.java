package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;

/**
 * @author DirectPlan
 */
@Getter
public class BowArrowHitEvent extends UserSkyWarsEvent {

    private final LivingEntity enemy;
    private final Arrow arrow;

    public BowArrowHitEvent(User user, LivingEntity enemy, Arrow arrow) {
        super(user);
        this.enemy = enemy;
        this.arrow = arrow;
    }
}
