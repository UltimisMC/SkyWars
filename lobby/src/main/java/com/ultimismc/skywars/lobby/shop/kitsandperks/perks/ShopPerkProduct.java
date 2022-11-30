package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserPurchasableProduct;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class ShopPerkProduct extends UserPurchasableProduct {

    private final Perk perk;
    private final PerkProduct perkProduct;

    public ShopPerkProduct(int itemSlot, Perk perk) {
        super(itemSlot, perk);

        this.perk = perk;
        perkProduct = new PerkProduct(perk);
    }

    public ShopPerkProduct(Perk perk) {
        this(0, perk);
    }

    @Override
    public ProductItemDesign designPurchasableProduct(User user) {
        if(!perk.isSoulWellPerk() && user.getAsset(perk) != null) return null;
        return perkProduct.designProduct(user);
    }

    @Override
    public void executePurchasableProduct(User user, ClickType clickType) {}
}
