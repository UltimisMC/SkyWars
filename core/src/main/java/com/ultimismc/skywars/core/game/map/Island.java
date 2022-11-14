package com.ultimismc.skywars.core.game.map;

import lombok.Data;
import org.bukkit.Location;

/**
 * @author DirectPlan
 */
@Data
public class Island {

    private boolean taken;
    private final Location cageLocation;
}
