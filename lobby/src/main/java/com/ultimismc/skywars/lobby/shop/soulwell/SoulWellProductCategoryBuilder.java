package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.kitsandperks.perks.ShopPerkProduct;
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

        productCategory.addProduct(new RollSoulWellProduct(featureHandler.getPlugin(), 13));

        SoulHarvestersCategory soulHarvestersCategory = new SoulHarvestersCategory(29);
        soulHarvestersCategory.addProduct(new SoulHarvesterProduct("Warlock", 11, 5, 600));
        soulHarvestersCategory.addProduct(new SoulHarvesterProduct("Necromancer", 13, 10, 1000));
        soulHarvestersCategory.addProduct(new SoulHarvesterProduct("Death God", 15, 50, 45000));
        productCategory.addProduct(soulHarvestersCategory);


        PerkManager perkManager = featureHandler.getPerkManager();
        Perk xezbethLuckPerk = perkManager.getPurchasable("Xezbeth Luck");
        Perk harvestingSeasonPerk = perkManager.getPurchasable("Harvesting Season");

        productCategory.addProduct(new ShopPerkProduct(31, xezbethLuckPerk));
        productCategory.addProduct(new ShopPerkProduct(33, harvestingSeasonPerk));
        return productCategory;
    }
}
