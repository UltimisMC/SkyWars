package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.level.LevelManager;

/**
 * @author DirectPlan
 */
public class UserLoadedListener {

    private final LevelManager levelManager;

    public UserLoadedListener(FeatureHandler featureHandler) {
        levelManager = featureHandler.getLevelManager();
    }

    public void onUserLoaded(User user) {
        levelManager.calculateUserLevel(user);
    }
}
