package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class PerkShopCategory extends UserProductCategory {

    public PerkShopCategory(int itemSlot) {
        super("Perk Shop", itemSlot, true);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        return new ProductItemDesign(Material.DIAMOND);
    }
}
