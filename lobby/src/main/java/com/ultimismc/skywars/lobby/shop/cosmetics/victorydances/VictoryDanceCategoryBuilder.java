package com.ultimismc.skywars.lobby.shop.cosmetics.victorydances;

import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.victorydances.VictoryDance;
import com.ultimismc.skywars.core.game.features.cosmetics.victorydances.VictoryDanceHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.cosmetics.CosmeticPurchasableProduct;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class VictoryDanceCategoryBuilder implements UserProductCategoryBuilder {

    private final CosmeticManager cosmeticManager;

    @Override
    public ProductCategory<User> buildCategory() {

        VictoryDanceHandler victoryDanceHandler = cosmeticManager.getVictoryDanceHandler();

        VictoryDancesCategory category = new VictoryDancesCategory(victoryDanceHandler);
        for(VictoryDance victoryDance : victoryDanceHandler) {
            category.addProduct(new CosmeticPurchasableProduct(victoryDanceHandler, victoryDance));
        }
        return category;
    }
}
