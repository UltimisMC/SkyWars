package com.ultimismc.skywars.core.game.map;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@Data
public class Map {

    private final String name;

    private final List<Island> islands = new ArrayList<>();

    public void addIsland(Island island) {
        islands.add(island);
    }
}
