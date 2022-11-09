package com.ultimismc.skywars.lobby.shop.level;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.MessageConfigKeys;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public class HideLevelProduct extends UserProduct {

    public HideLevelProduct(int itemSlot) {
        super("Hide & Show Level", itemSlot);
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        String displayName = "&cHide Level";
        boolean scramble = user.isScramble();
        short durability = 10;
        if(scramble) {
            displayName = "&aShow Level";
            durability = 8;
        }

        List<String> lore = Arrays.asList("&7Toggles whether your SkyWars",
                "&7Level and Prestige Icon show",
                "&7next to your name.",
                " ",
                (scramble ? "&eClick to show!" : "&eClick to hide!"));

        ProductItemDesign productItemDesign = new ProductItemDesign(Material.INK_SACK, durability, ChatColor.WHITE, lore);
        productItemDesign.setDisplayName(displayName);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user) {
        Player player = user.getPlayer();
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);

        boolean scramble = user.isScramble();
        user.setScramble(scramble = (!scramble));

        if(scramble) {
            MessageConfigKeys.LEVEL_LEVEL_SHOWN.sendMessage(player);
            return;
        }
        MessageConfigKeys.LEVEL_LEVEL_HIDDEN.sendMessage(player);
    }
}
