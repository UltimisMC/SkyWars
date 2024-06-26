package com.ultimismc.skywars.core.user.asset;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.user.UserCacheHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author DirectPlan
 */
public class UserAssetsHandler extends UserCacheHandler<String, UserAsset> {

    public UserAsset getAsset(String assetName) {
        return getCache(assetName);
    }

    public UserAsset getAsset(Purchasable purchasable, GameType gameType) {
        UserAsset userAsset = getAsset(purchasable.getNameWithCategory());
        if(userAsset == null) return null;
        if(gameType == null || userAsset.isPurchasedFor(gameType)) return userAsset;
        return null;
    }

    public void addAsset(UserAsset asset, GameType gameType) {
        asset.addPurchasedGame(gameType);
        addCache(asset.getNameWithCategory(), asset);
    }

    public void purchaseAsset(Purchasable purchasable, GameType gameType) {
        UserAsset userAsset = getAsset(purchasable, gameType);
        if(userAsset == null) {
            UserAsset asset = new UserAsset(purchasable, System.currentTimeMillis(), false);
            addAsset(asset, gameType);
        }
    }

    public boolean hasPurchased(Purchasable purchasable, GameType gameType) {
        return getAsset(purchasable, gameType) != null;
    }

    public <T extends Purchasable> List<T> getAssetPurchasables(Class<T> clazz) {
        List<T> assets = new ArrayList<>();
        for(UserAsset userAsset : getAssets()) {
            Purchasable purchasable = userAsset.getPurchasable();
            if(!clazz.isInstance(purchasable)) continue;
            assets.add(clazz.cast(purchasable));
        }
        return assets;
    }

    public <T extends Purchasable> List<UserAsset> getAssets(Class<T> clazz) {
        List<UserAsset> assets = new ArrayList<>();
        for(UserAsset userAsset : getAssets()) {
            Purchasable purchasable = userAsset.getPurchasable();
            if(!clazz.isInstance(purchasable)) continue;
            assets.add(userAsset);
        }
        return assets;
    }

    public Collection<UserAsset> getAssets() {
        return getCacheCollection();
    }
}
