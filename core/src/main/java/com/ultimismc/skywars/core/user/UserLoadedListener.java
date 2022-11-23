package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.level.LevelManager;
import com.ultimismc.skywars.core.rank.RankManager;
import com.ultimismc.skywars.core.user.asset.UserAsset;

import java.util.Locale;

/**
 * @author DirectPlan
 */
public class UserLoadedListener {

    private final LevelManager levelManager;
    private final RankManager rankManager;
    private final FeatureHandler featureHandler;

    public UserLoadedListener(SkyWarsPlugin plugin) {
        featureHandler = plugin.getFeatureHandler();
        rankManager = plugin.getRankManager();
        levelManager = featureHandler.getLevelManager();
    }

    public void onUserLoaded(User user) {
        levelManager.calculateUserLevel(user);
        rankManager.setupUserRank(user);

        if(!user.hasEmptySettings()) return;

        for(Purchasable defaultPurchasable : featureHandler.getDefaultPurchasables()) {
            String category = defaultPurchasable.getCategory();
            category = category.toLowerCase(Locale.ROOT);
            user.addSetting(category, defaultPurchasable);
            user.addAsset(new UserAsset(defaultPurchasable));
        }
    }
}
