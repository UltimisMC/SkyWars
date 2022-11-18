package com.ultimismc.skywars.game.chest;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

/**
 * @author DirectPlan
 */
@Data
public class Chest {

    private final Block block;
    private final boolean midChest;
    private final ChestHologram chestHologram = new ChestHologram();
    private boolean opened;
    private ChestSkyWarsEventUpdater updater;

    public Location getLocation() {
        return block.getLocation();
    }

    public org.bukkit.block.Chest getBlockChest() {
        return (org.bukkit.block.Chest) block.getState();
    }

    public boolean isEmpty() {
        org.bukkit.block.Chest chest = getBlockChest();
        Inventory inventory = chest.getBlockInventory();
        return inventory.firstEmpty() == -1;
    }
}
