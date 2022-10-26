package com.ultimismc.skywars.lobby.shop.kitsandperks;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.kits.KitManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class KitsPerksProductCategoryBuilder implements UserProductCategoryBuilder {

    private final FeatureHandler featureHandler;

    @Override
    public ProductCategory<User> buildCategory() {

        KitManager kitManager = featureHandler.getKitManager();
        KitsPerksProductCategory productCategory = new KitsPerksProductCategory(10);



        return productCategory;
    }
}
