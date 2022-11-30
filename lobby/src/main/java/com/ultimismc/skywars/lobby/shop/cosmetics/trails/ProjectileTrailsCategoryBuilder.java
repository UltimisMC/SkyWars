package com.ultimismc.skywars.lobby.shop.cosmetics.trails;

import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.projectiletrails.ProjectileTrail;
import com.ultimismc.skywars.core.game.features.cosmetics.projectiletrails.ProjectileTrailHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.cosmetics.CosmeticPurchasableProduct;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ProjectileTrailsCategoryBuilder implements UserProductCategoryBuilder {

    private final CosmeticManager cosmeticManager;

    @Override
    public ProductCategory<User> buildCategory() {

        ProjectileTrailHandler projectileTrailHandler = cosmeticManager.getProjectileTrailHandler();

        ProjectileTrailsCategory category = new ProjectileTrailsCategory(projectileTrailHandler, 10);
        for(ProjectileTrail projectileTrail : projectileTrailHandler) {
            category.addProduct(new CosmeticPurchasableProduct(projectileTrail, false));
        }
        return category;
    }
}
