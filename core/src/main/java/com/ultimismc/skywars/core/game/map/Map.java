package com.ultimismc.skywars.core.game.map;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Optional;

/**
 * @author DirectPlan
 */
@Getter
public class Map {

    @Setter private String name;
    private final java.util.Map<Location, Island> islands;
    private final java.util.Map<Location, Chest> chests;

    public Map(String name, java.util.Map<Location, Island> islands, java.util.Map<Location, Chest> chests) {
        this.name = name;
        this.islands = islands;
        this.chests = chests;
    }

    public Map(String name) {
        this(name, new HashMap<>(), new HashMap<>());
    }

    public void addIsland(Island island) {
        islands.put(island.getCageLocation(), island);
    }

    public Island getIsland(Location location) {
        return islands.get(location);
    }

    public Island getAvailableIsland() {
        Optional<Island> optionalIsland = islands.values().stream().filter(island -> !island.isTaken()).findFirst();
        return optionalIsland.orElse(null);
    }

    public void removeIsland(Location location) {
        islands.remove(location);
    }

    public void removeIsland(Island island) {
        removeIsland(island.getCageLocation());
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
