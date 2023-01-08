package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Getter
public class UserDamagedEvent extends UserSkyWarsEvent {

    private final User damaged;
    @Setter private double damage;
    private final boolean environmentalDamage;

    public UserDamagedEvent(User user, User damaged, boolean environmentalDamage) {
        super(user);

        this.damage = 1.0;
        this.damaged = damaged;
        this.environmentalDamage = environmentalDamage;
    }
}
