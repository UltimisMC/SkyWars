package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 *
 * TODO: Add a simplifier for purchasable products.
 */
public class SoulHarvesterProduct extends UserProduct {

    private final Currency currency = Currency.COIN_CURRENCY;

    private final String harvesterName;
    private final int souls;
    private final int cost;

    public SoulHarvesterProduct(String harvesterName, int souls, int cost, int itemSlot) {
        super("Hire: " + harvesterName, 4, itemSlot);
        this.harvesterName = harvesterName;
        this.souls = souls;
        this.cost = cost;
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        String status = "&eClick to purchase!";
        if(!currency.canAfford(user, cost)) {
            status = "&cCannot afford.";
        }

        List<String> lore = Arrays.
                asList("&7Hire a " + harvesterName + " to go to battle",
                        "&b" + souls + " Souls &7for you.",
                        " ",
                        "&7Cost: " + currency.getDisplayPrice(cost),
                        " ",
                        status);

        ProductItemDesign productItemDesign = new ProductItemDesign(Material.PAPER, lore);
        productItemDesign.setAmount(souls);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user) {
        if(!currency.canAfford(user, cost)) {
            user.sendMessage("&fYo bro you can't afford this shit rn");
            return;
        }
        currency.decreaseCurrency(user, cost);
        user.sendMessage("let them harvest!");
    }
}
