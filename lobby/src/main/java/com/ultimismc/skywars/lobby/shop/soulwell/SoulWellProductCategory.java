package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class SoulWellProductCategory extends UserProductCategory {

    public SoulWellProductCategory(int itemSlot) {
        super("Soul Well", itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {

        UserStatistics statistics = user.getStatistics();
        int souls = statistics.getSouls();

        List<String> lore = ShopMessageKeys.SOUL_WELL_CATEGORY_LORE.getStringList(new Replacement("souls", souls));
        return new ProductItemDesign(Material.ENDER_PORTAL_FRAME, ChatColor.AQUA, lore);
    }

}
