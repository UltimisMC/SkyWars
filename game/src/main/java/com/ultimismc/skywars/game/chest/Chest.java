package com.ultimismc.skywars.game.chest;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@Data
public class Chest {

    private final Block block;
    private final boolean midChest;
    private final ChestHologram chestHologram = new ChestHologram();
    private boolean opened;
    private ChestHologramUpdater updater;

    private final Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST);

    public Location getLocation() {
        return block.getLocation();
    }

    public org.bukkit.block.Chest getBlockChest() {
        return (org.bukkit.block.Chest) block.getState();
    }

    public boolean isEmpty() {
        return PluginUtility.isEmpty(inventory);
    }

    public void destroyHologram() {
        chestHologram.destroy();
    }
}
