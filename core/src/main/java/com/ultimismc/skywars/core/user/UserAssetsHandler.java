package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class UserAssetsHandler {

    private final Map<String, UserAsset> userAssets = new HashMap<>();

    public UserAsset getAsset(String assetName) {
        return userAssets.get(assetName);
    }

    public UserAsset getAsset(Purchasable purchasable) {
        for(UserAsset asset : userAssets.values()) {
            if(asset.getPurchasable() != purchasable) continue;
            return asset;
        }
        return null;
    }

    public void addAsset(UserAsset asset) {
        userAssets.put(asset.getName(), asset);
    }

    public void purchaseAsset(User user, Purchasable purchasable) {
        UserAsset userAsset = new UserAsset(purchasable, System.currentTimeMillis(), false);
        addAsset(userAsset);
        Currency currency = purchasable.getCurrency();
        currency.decreaseCurrency(user, purchasable);
    }

    public Collection<UserAsset> getAssets() {
        return userAssets.values();
    }
}
