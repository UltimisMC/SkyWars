package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserAsset;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class PurchasedPerkProduct extends PerkProduct {

    public PurchasedPerkProduct(Perk perk) {
        super(perk);
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        UserAsset perkAsset = user.getAsset(perk);
        if(perkAsset == null) return null;

        ProductItemDesign productItemDesign = super.designProduct(user);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user) {
        user.sendMessage("Goofy ahh");
    }
}
