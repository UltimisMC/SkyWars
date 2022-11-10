package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.game.features.soulwell.SoulPerk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class SoulWellPerkProduct extends UserProduct {

    private final SoulPerk soulPerk;

    public SoulWellPerkProduct(SoulPerk soulPerk, int itemSlot) {
        super(soulPerk.getName() + " Perk", itemSlot);
        this.soulPerk = soulPerk;
    }

    @Override
    public ProductItemDesign designProduct(User user) {

        return null;
    }

    @Override
    public void executeAction(User user) {

    }
}
