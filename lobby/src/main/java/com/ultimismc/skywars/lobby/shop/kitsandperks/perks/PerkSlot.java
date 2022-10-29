package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public class PerkSlot extends UserProduct {

    private final int perkSlot;

    public PerkSlot(int perkSlot, int itemSlot) {
        super("Perk Slot", itemSlot);
        this.perkSlot = perkSlot;
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        List<String> lore = Arrays.asList("&eClick here to view your",
                "&aPurchased Perks &ein the chest",
                "&eicon below to equip perks in",
                "&ethis slot!");
        String displayName = "&cPerk Slot #" + perkSlot + " - " + "Empty";
        ProductItemDesign productItemDesign = new ProductItemDesign(Material.STAINED_GLASS_PANE, (short) 14, ChatColor.RED, lore, true);
        productItemDesign.setDisplayName(displayName);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user) {
        user.sendMessage("Perk Slot: " + perkSlot);
    }
}
