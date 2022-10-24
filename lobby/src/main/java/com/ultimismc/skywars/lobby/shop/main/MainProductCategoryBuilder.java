package com.ultimismc.skywars.lobby.shop.main;

import com.ultimismc.skywars.lobby.shop.ShopProductCategory;
import com.ultimismc.skywars.lobby.shop.SkyWarsShopHandler;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.user.User;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class MainProductCategoryBuilder implements UserProductCategoryBuilder {

    private final SkyWarsShopHandler shopHandler;

    @Override
    public ProductCategory<User> buildCategory() {
        ShopProductCategory productCategory = new ShopProductCategory();

        productCategory.addProduct(new GayProduct());
        return productCategory;
    }
}
