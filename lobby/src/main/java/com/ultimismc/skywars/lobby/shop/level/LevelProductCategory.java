package com.ultimismc.skywars.lobby.shop.level;

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
public class LevelProductCategory extends UserProductCategory {

    public LevelProductCategory(int itemSlot) {
        super("SkyWars Level Progression", itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {

        List<String> lore = ShopMessageKeys.LEVEL_PROGRESSION_CATEGORY_LORE.getStringList();
        return new ProductItemDesign(Material.NETHER_STAR, ChatColor.LIGHT_PURPLE, lore);
    }

}
