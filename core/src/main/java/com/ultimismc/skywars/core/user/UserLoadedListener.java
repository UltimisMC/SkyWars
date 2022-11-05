package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.level.LevelManager;
import com.ultimismc.skywars.core.rank.RankManager;

/**
 * @author DirectPlan
 */
public class UserLoadedListener {

    private final LevelManager levelManager;
    private final RankManager rankManager;

    public UserLoadedListener(SkyWarsPlugin plugin) {
        FeatureHandler featureHandler = plugin.getFeatureHandler();
        levelManager = featureHandler.getLevelManager();
        rankManager = plugin.getRankManager();
    }

    public void onUserLoaded(User user) {
        levelManager.calculateUserLevel(user);
        rankManager.setupUserRank(user);
    }
}
