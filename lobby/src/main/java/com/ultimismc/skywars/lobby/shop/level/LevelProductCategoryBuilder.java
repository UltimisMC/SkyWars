package com.ultimismc.skywars.lobby.shop.level;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
public class LevelProductCategoryBuilder implements UserProductCategoryBuilder {

    @Override
    public ProductCategory<User> buildCategory() {

        LevelProductCategory productCategory = new LevelProductCategory(16);

        return productCategory;
    }
}
