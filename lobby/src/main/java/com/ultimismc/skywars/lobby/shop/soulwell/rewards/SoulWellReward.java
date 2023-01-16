package com.ultimismc.skywars.lobby.shop.soulwell.rewards;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public interface SoulWellReward {

    String getDisplayName();

    PurchasableRarity getRarity();

    PurchasableDesign getDesign();

    int getRarityCoins();

    void giveReward(User user);

    default boolean hasPurchased(User user) {
        return false;
    }
}
