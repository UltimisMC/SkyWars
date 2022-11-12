package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserPurchasableProduct;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public class RollSoulWellProduct extends UserPurchasableProduct {

    public RollSoulWellProduct(int itemSlot) {
        super("Roll Soul Well", itemSlot, 50, Currency.SOUL_CURRENCY);
    }

    @Override
    public ProductItemDesign designPurchasableProduct(User user) {

        String status = "&eClick to roll!";

        List<String> lore = Arrays.asList("&7Rolls for a random kit, perk,",
                "&7or coin bonus.",
                " ");
        ProductItemDesign productItemDesign = new ProductItemDesign(Material.ENDER_PORTAL_FRAME, lore);
        productItemDesign.setPurchaseStatus(status);
        return productItemDesign;
    }

    @Override
    public void executePurchasableProduct(User user) {
        user.sendMessage("let them roll!");
    }
}
