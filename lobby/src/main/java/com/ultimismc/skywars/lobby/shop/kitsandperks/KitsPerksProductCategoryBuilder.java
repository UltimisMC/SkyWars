package com.ultimismc.skywars.lobby.shop.kitsandperks;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
public class KitsPerksProductCategoryBuilder implements UserProductCategoryBuilder {

    @Override
    public ProductCategory<User> buildCategory() {

        KitsPerksProductCategory productCategory = new KitsPerksProductCategory(10);

        return productCategory;
    }
}
