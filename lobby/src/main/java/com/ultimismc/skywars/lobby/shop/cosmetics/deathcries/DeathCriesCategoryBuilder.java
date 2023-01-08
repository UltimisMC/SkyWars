package com.ultimismc.skywars.lobby.shop.cosmetics.deathcries;

import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCry;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCryHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class DeathCriesCategoryBuilder implements UserProductCategoryBuilder {

    private final CosmeticManager cosmeticManager;

    @Override
    public ProductCategory<User> buildCategory() {

        DeathCryHandler deathCryHandler = cosmeticManager.getDeathCryHandler();
        DeathCriesCategory category = new DeathCriesCategory(deathCryHandler, 28);
        for(DeathCry deathCry : deathCryHandler) {
            category.addProduct(new DeathCryPurchasableProduct(deathCryHandler, deathCry));
        }
        return category;
    }
}
