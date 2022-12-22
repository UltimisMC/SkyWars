package com.ultimismc.skywars.core.game.features.perks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class UserPerk {

    @Setter private boolean enabled;
    @Setter private int threshold;
}
