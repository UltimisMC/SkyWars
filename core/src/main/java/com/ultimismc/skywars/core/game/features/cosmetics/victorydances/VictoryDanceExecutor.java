package com.ultimismc.skywars.core.game.features.cosmetics.victorydances;

import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public interface VictoryDanceExecutor {

    void executeDance(User user, int tick);
}
