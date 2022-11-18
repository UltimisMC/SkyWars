package com.ultimismc.skywars.core.game;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Getter
public class Map {

    @Setter private String name;

    public Map(String name) {
        this.name = name;
    }
}
