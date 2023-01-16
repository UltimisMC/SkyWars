package com.ultimismc.skywars.lobby.shop.soulwell.rewards;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class SoulWellPurchasableReward implements SoulWellReward {

    private final Purchasable purchasable;
    private final GameType gameType;

    @Override
    public void giveReward(User user) {
        user.addAsset(new UserAsset(purchasable), gameType);
    }

    @Override
    public String getDisplayName() {
        return purchasable.getNameWithCategory() + " (" + gameType.getName() + ")";
    }

    @Override
    public PurchasableRarity getRarity() {
        return purchasable.getRarity();
    }

    @Override
    public PurchasableDesign getDesign() {
        return purchasable.getDesign();
    }

    @Override
    public int getRarityCoins() {
        switch (getRarity()) {
            case RARE: {
                return 100;
            }
            case EPIC: {
                return 500;
            }
            case LEGENDARY: {
                return 1000;
            }
            default: { // Common
                return 50;
            }
        }
    }

    @Override
    public boolean hasPurchased(User user) {
        return user.hasPurchased(purchasable, gameType);
    }
}
