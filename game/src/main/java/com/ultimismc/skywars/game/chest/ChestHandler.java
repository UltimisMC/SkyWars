package com.ultimismc.skywars.game.chest;

import com.ultimismc.skywars.game.events.SkyWarsEventHandler;
import com.ultimismc.skywars.game.events.SkyWarsEventUpdater;
import com.ultimismc.skywars.game.handler.Game;
import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import xyz.directplan.directlib.PluginUtility;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class ChestHandler {

    @Getter private final Map<Location, Chest> chests = new HashMap<>();

    private final GameHandler gameHandler;
    private final SkyWarsEventHandler skyWarsEventHandler;

    public ChestHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        skyWarsEventHandler = gameHandler.getSkyWarsEventHandler();
    }

    public void refillChest(Chest chest) {
        Game game = gameHandler.getGame();
        GameChestRegistry chestRegistry = game.getChestRegistry();
        chestRegistry.refillChest(chest);

        if(!chest.isOpened()) return;
        chest.setOpened(false);
        PluginUtility.playChestAction(chest.getBlockChest(), false);

        ChestHologram chestHologram = chest.getChestHologram();
        if(chestHologram != null) chestHologram.destroy();

        SkyWarsEventUpdater updater = chest.getUpdater();
        skyWarsEventHandler.removeUpdater(updater);
    }

    public void refillAllChests() {
        chests.forEach((location, chest) -> refillChest(chest));
    }

    public void openChest(Block block) {
        Chest chest = getChest(block);
        chest.setOpened(true);
        ChestSkyWarsEventUpdater updater = new ChestSkyWarsEventUpdater(chest);
        skyWarsEventHandler.addUpdater(updater);
    }

    public void shutdown() {
        for(Chest chest : chests.values()) {
            org.bukkit.block.Chest blockChest = chest.getBlockChest();
            Inventory blockInventory = blockChest.getBlockInventory();
            if(blockInventory != null) {
                blockInventory.clear();
            }

            ChestHologram hologram = chest.getChestHologram();
            hologram.destroy();
        }
    }

    public void addChest(Chest chest) {
        chests.put(chest.getLocation(), chest);
    }

    public void addChest(Block block, boolean midChest) {
        addChest(new Chest(block, midChest));
    }

    public Chest getChest(Location location) {
        return chests.get(location);
    }

    public Chest getChest(Block block) {
        Location location = block.getLocation();
        return getChest(location);
    }

    public void removeChest(Location location) {
        chests.remove(location);
    }

    public void removeChest(Block block) {
        Location location = block.getLocation();
        removeChest(location);
    }

    public int getSize() {
        return chests.size();
    }
}
