package com.ultimismc.skywars.lobby.shop.cosmetics;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class CosmeticProductCategory extends UserProductCategory {

    public CosmeticProductCategory(int itemSlot) {
        super("My Cosmetics", itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {

        List<String> lore = ShopMessageKeys.MY_COSMETICS_CATEGORY_LORE.getStringList();
        return new ProductItemDesign(Material.SLIME_BALL, ChatColor.GREEN, lore);
    }

}
