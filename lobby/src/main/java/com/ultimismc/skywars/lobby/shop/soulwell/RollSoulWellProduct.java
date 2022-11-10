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
 */
public class RollSoulWellProduct extends UserProduct {

    private final int cost = 50;
    private final Currency currency = Currency.SOUL_CURRENCY;

    public RollSoulWellProduct(int itemSlot) {
        super("Roll Soul Well", itemSlot);
    }

    @Override
    public ProductItemDesign designProduct(User user) {

        String status = "&eClick to roll!";
        if(!currency.canAfford(user, cost)) {
            status = "&cCannot afford.";
        }
        List<String> lore = Arrays.asList("&7Rolls for a random kit, perk,",
                "&7or coin bonus.",
                " ",
                "&7Cost: " + currency.getDisplayPrice(cost) + " Souls",
                "",
                status);
        return new ProductItemDesign(Material.ENDER_PORTAL_FRAME, lore);
    }

    @Override
    public void executeAction(User user) {
        if(!currency.canAfford(user, cost)) {
            user.sendMessage("&fYo bro you can't afford this shit rn");
            return;
        }
        currency.decreaseCurrency(user, cost);
        user.sendMessage("let them roll!");
    }
}
