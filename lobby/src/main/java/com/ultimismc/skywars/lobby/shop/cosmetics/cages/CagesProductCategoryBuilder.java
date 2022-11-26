package com.ultimismc.skywars.lobby.shop.cosmetics.cages;

import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.Cage;
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
        CagesProductCategory cagesProductCategory = new CagesProductCategory(12);
        for(Cage cage : cosmeticManager.getCageHandler()) {
            cagesProductCategory.addProduct(new CosmeticPurchasableProduct(cage));
        }
        return cagesProductCategory;
    }
}
