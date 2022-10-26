package com.ultimismc.skywars.lobby.shop.level;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LevelProductCategoryBuilder implements UserProductCategoryBuilder {

    private final FeatureHandler featureHandler;

    @Override
    public ProductCategory<User> buildCategory() {

        LevelProductCategory productCategory = new LevelProductCategory(16);

        return productCategory;
    }
}
