package com.ultimismc.skywars.lobby.shop.cosmetics.cages;

import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.Cage;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.CageHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.cosmetics.CosmeticPurchasableProduct;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class CagesProductCategoryBuilder implements UserProductCategoryBuilder {

    private final CosmeticManager cosmeticManager;

    @Override
    public ProductCategory<User> buildCategory() {
        CageHandler cageHandler = cosmeticManager.getCageHandler();
        CagesProductCategory cagesProductCategory = new CagesProductCategory(cageHandler, 12);
        for(Cage cage : cageHandler) {
            cagesProductCategory.addProduct(new CosmeticPurchasableProduct(cageHandler, cage, false));
        }
        return cagesProductCategory;
    }
}
