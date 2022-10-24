package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
public class SoulWellProductCategoryBuilder implements UserProductCategoryBuilder {

    @Override
    public ProductCategory<User> buildCategory() {

        SoulWellProductCategory productCategory = new SoulWellProductCategory(12);

        return productCategory;
    }
}
