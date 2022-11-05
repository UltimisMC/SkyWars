package com.ultimismc.skywars.core.game.features.level;

import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public interface LevelReward {

    String getDisplayName();

    void rewardPlayer(User user);
}
