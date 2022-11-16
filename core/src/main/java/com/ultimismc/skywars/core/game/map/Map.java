package com.ultimismc.skywars.core.game.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public class Map {

    @Setter private String name;
    private final List<Island> islands;
    private final java.util.Map<Location, Chest> chests;

    public Map(String name, List<Island> islands, java.util.Map<Location, Chest> chests) {
        this.name = name;
        this.islands = islands;
        this.chests = chests;
    }

    public Map(String name) {
        this(name, new ArrayList<>(), new HashMap<>());
    }

    public void addIsland(Island island) {
        islands.add(island);
    }

    public void addChest(Chest chest) {
        chests.put(chest.getLocation(), chest);
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

    public void addChest(Block block, boolean midChest) {
        addChest(new Chest(block, midChest));
    }
}
