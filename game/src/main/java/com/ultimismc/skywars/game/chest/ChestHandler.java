package com.ultimismc.skywars.game.chest;

import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class ChestHandler {

    private final GameHandler gameHandler;
    @Getter private final Map<Location, Chest> chests = new HashMap<>();


    public ChestHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    public void refillChest(Chest chest) {

        boolean midChest = chest.isMidChest();

    }

    public void refillAllChests() {
        chests.forEach((location, chest) -> refillChest(chest));
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
