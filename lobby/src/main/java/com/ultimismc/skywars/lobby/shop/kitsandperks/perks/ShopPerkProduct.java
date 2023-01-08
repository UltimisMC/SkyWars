package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.GameType;
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
    private final GameType gameType;
    private final PerkProduct perkProduct;

    public ShopPerkProduct(int itemSlot, Perk perk, GameType gameType) {
        super(itemSlot, perk, gameType);

        this.perk = perk;
        this.gameType = gameType;
        perkProduct = new PerkProduct(perk);
    }

    public ShopPerkProduct(Perk perk, GameType gameType) {
        this(0, perk, gameType);
    }

    public ShopPerkProduct(int itemSlot, Perk perk) {
        this(itemSlot, perk, null);
    }

    @Override
    public ProductItemDesign designPurchasableProduct(User user) {
        if(!perk.isSoulWellPerk() && user.getAsset(perk, gameType) != null) return null;
        return perkProduct.designProduct(user);
    }

    @Override
    public void executePurchasableProduct(User user, GameType gameType, ClickType clickType) {}
}
