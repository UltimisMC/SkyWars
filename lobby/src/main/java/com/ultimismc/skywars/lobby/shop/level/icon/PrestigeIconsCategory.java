package com.ultimismc.skywars.lobby.shop.level.icon;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public class PrestigeIconsCategory extends UserProductCategory {

    public PrestigeIconsCategory(int itemSlot) {
        super("Prestige Icons", itemSlot, true);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        Player player = user.getPlayer();
        String userChatLevelPrefix = user.getLevelDisplayName(true);
        List<String> lore = Arrays.asList("&7Select a Prestige Icon to",
                "&7display next to your name in",
                "&7chat. You can unlock new",
                "&7Prestige Icons by prestiging",
                "&7your SkyWars Level.",
                " ",
                userChatLevelPrefix + " " + player.getDisplayName());
        return new ProductItemDesign(Material.NETHER_STAR, ChatColor.GOLD, lore);
    }
}
