package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class SoulWellProductCategoryBuilder implements UserProductCategoryBuilder {

    private final FeatureHandler featureHandler;
    private final int categorySlot;

    @Override
    public ProductCategory<User> buildCategory() {

        SoulWellProductCategory productCategory = new SoulWellProductCategory(categorySlot);

        productCategory.addProduct(new RollSoulWellProduct(13));

        SoulHarvestersCategory soulHarvestersCategory = new SoulHarvestersCategory(29);
        soulHarvestersCategory.addProduct(new SoulHarvesterProduct("Warlock", 5, 600, 11));
        soulHarvestersCategory.addProduct(new SoulHarvesterProduct("Necromancer", 10, 1000, 13));
        soulHarvestersCategory.addProduct(new SoulHarvesterProduct("Death God", 50, 45000, 15));
        productCategory.addProduct(soulHarvestersCategory);

        return productCategory;
    }
}
