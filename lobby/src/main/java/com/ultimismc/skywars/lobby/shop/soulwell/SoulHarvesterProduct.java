package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserPurchasableProduct;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 *
 */
public class SoulHarvesterProduct extends UserPurchasableProduct {

    private final String harvesterName;
    private final int souls;

    public SoulHarvesterProduct(String harvesterName, int itemSlot, int souls, int cost) {
        super("Hire: " + harvesterName, 4, itemSlot, cost, Currency.COIN_CURRENCY);
        this.harvesterName = harvesterName;
        this.souls = souls;
    }

    @Override
    public ProductItemDesign designPurchasableProduct(User user) {
        List<String> lore = Arrays.
                asList("&7Hire a " + harvesterName + " to go to battle",
                        "&b" + souls + " Souls &7for you.",
                        " ");

        ProductItemDesign productItemDesign = new ProductItemDesign(Material.PAPER, lore);
        productItemDesign.setAmount(souls);
        return productItemDesign;
    }

    @Override
    public void executePurchasableProduct(User user) {
        user.sendMessage("let them harvest!");
    }
}
