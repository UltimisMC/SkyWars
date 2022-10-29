package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class PurchasedPerksCategory extends UserProductCategory {

    public PurchasedPerksCategory(int itemSlot) {
        super("Purchased Perks", itemSlot, true);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        return new ProductItemDesign(Material.CHEST);
    }
}
