package com.ultimismc.skywars.lobby.shop.cosmetics;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.main.GayProduct;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
public class CosmeticsProductCategoryBuilder implements UserProductCategoryBuilder {

    @Override
    public ProductCategory<User> buildCategory() {

        CosmeticProductCategory productCategory = new CosmeticProductCategory(14);

        productCategory.addProduct(new GayProduct());
        return productCategory;
    }
}
