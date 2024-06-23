package com.ultimismc.skywars.game.island;

import com.ultimismc.skywars.core.game.features.cosmetics.cages.Cage;
import com.ultimismc.skywars.game.chest.Chest;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Data
public class Island {

    private final Map<Block, Chest> chests = new HashMap<>();

    private Location cageLocation;
    private Cage cage;
    private boolean taken;

    public void addChest(Block block, Chest chest) {
        chests.put(block, chest);
    }

    public void removeChest(Block block) {
        chests.remove(block);
    }

    public Chest getChest(Block block) {
        return chests.get(block);
    }

    public boolean isConfigured() {
        return cageLocation != null;
    }
}
