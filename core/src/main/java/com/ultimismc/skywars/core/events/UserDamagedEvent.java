package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
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

    public UserDamagedEvent(GameConfig gameConfig, User user, User damaged, double damage, boolean environmentalDamage) {
        super(gameConfig, user);

        this.damage = damage;
        this.damaged = damaged;
        this.environmentalDamage = environmentalDamage;
    }
}
