package com.ultimismc.skywars.lobby.shop.main;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.lobby.shop.ShopProductCategory;
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

    private final FeatureHandler featureHandler;

    @Override
    public ProductCategory<User> buildCategory() {
        ShopProductCategory productCategory = new ShopProductCategory();


        productCategory.buildProductCategory(new KitsPerksProductCategoryBuilder(featureHandler));
        productCategory.buildProductCategory(new SoulWellProductCategoryBuilder(featureHandler));
        productCategory.buildProductCategory(new CosmeticsProductCategoryBuilder(featureHandler));
        productCategory.buildProductCategory(new LevelProductCategoryBuilder(featureHandler));

        return productCategory;
    }
}
