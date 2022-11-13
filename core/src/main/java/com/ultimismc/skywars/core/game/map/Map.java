package com.ultimismc.skywars.core.game.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class Map {

    private final String name;
    private final List<Island> islands;

    public Map(String name) {
        this(name, new ArrayList<>());
    }

    public void addIsland(Island island) {
        islands.add(island);
    }
}
