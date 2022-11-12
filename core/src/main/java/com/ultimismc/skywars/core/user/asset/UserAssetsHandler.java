package com.ultimismc.skywars.core.user.asset;

import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.user.UserCacheHandler;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public class UserAssetsHandler extends UserCacheHandler<String, UserAsset> {

    public UserAsset getAsset(String assetName) {
        return getCache(assetName);
    }

    public UserAsset getAsset(Purchasable purchasable) {
        return getAsset(purchasable.getName());
    }

    public void addAsset(UserAsset asset) {
        addCache(asset.getName(), asset);
    }

    public void purchaseAsset(Purchasable purchasable) {
        UserAsset userAsset = new UserAsset(purchasable, System.currentTimeMillis(), false);
        addAsset(userAsset);
    }

    public boolean isPurchased(Purchasable purchasable) {
        return getAsset(purchasable) != null;
    }

    public Collection<UserAsset> getAssets() {
        return getCacheCollection();
    }
}
