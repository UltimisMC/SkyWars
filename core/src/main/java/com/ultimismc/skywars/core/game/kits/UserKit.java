package com.ultimismc.skywars.core.game.kits;

import lombok.Data;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Data
public class UserKit {

    private final Kit kit;

    @Setter private boolean unlocked;
}
