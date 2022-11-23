package com.ultimismc.skywars.core.game.features;

import org.bukkit.ChatColor;
import xyz.directplan.directlib.inventory.MenuItem;

/**
 * @author DirectPlan
 */
public class PurchasableItem extends MenuItem {

    private final Purchasable purchasable;

    public PurchasableItem(Purchasable purchasable, ChatColor nameColor) {
        super(purchasable.getDisplayMaterial(), nameColor + purchasable.getName());
        this.purchasable = purchasable;
    }
}
