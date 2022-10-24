package com.ultimismc.skywars.lobby.shop.main;

import com.ultimismc.skywars.lobby.shop.ShopProductCategory;
import com.ultimismc.skywars.lobby.shop.SkyWarsShopHandler;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.cosmetics.CosmeticsProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.kitsandperks.KitsPerksProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.level.LevelProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.soulwell.SoulWellProductCategoryBuilder;
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


        productCategory.buildProductCategory(new KitsPerksProductCategoryBuilder());
        productCategory.buildProductCategory(new SoulWellProductCategoryBuilder());
        productCategory.buildProductCategory(new CosmeticsProductCategoryBuilder());
        productCategory.buildProductCategory(new LevelProductCategoryBuilder());

        return productCategory;
    }
}
