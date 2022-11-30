package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.user.User;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class ShopProductCategory extends UserProductCategory {


    public ShopProductCategory() {
        super("SkyWars Shop", 0, 6);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        return null;
    }

    @Override
    public void executeAction(User user, ClickType clickType) {}
}
