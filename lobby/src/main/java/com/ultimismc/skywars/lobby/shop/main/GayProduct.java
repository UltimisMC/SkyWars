package com.ultimismc.skywars.lobby.shop.main;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 *
 * STAY TILL THE END
 */
public class GayProduct extends UserProduct {

    private int order;
    public GayProduct(int order) {
        super("A gay product #" + order, 10);

        this.order = order;
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        List<String> lore = Arrays.asList("&c&lWarning:",
                "&7This product is very gay",
                "&7if you click on this product,",
                "&7you risk becoming gay &b" + order + "x &7times! BEWARE!!");
        return new ProductItemDesign(Material.LEATHER_HELMET, lore);
    }

    @Override
    public void executeAction(User user, ClickType clickType) {
        user.sendMessage("YOUR NAME IS " + user.getName() + " and clicked product is " + order);
    }
}
