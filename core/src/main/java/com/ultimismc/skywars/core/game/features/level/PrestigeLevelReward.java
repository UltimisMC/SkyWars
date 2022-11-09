package com.ultimismc.skywars.core.game.features.level;

import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class PrestigeLevelReward implements LevelReward {

    private final Prestige prestige;

    @Override
    public String getDisplayName() {
        return prestige.getDisplayName();
    }

    @Override
    public void rewardPlayer(User user) {}
}
