package com.ultimismc.skywars.lobby.shop.main;

import com.ultimismc.skywars.lobby.shop.UserProduct;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public class GayProduct extends UserProduct {

    public GayProduct() {
        super("A gay product", 10);
    }

    @Override
    public ProductItemDesign designItem(User user) {
        List<String> lore = Arrays.asList("&c&lWarning:",
                "&7This product is very gay",
                "&7if you click on this product,",
                "&7you risk becoming gay too! BEWARE!!");
        return new ProductItemDesign(Material.LEATHER_HELMET, lore);
    }

    @Override
    public void executeAction(User user) {
        user.sendMessage("This worked!! and YOUR NAME IS " + user.getName());
    }
}
