package com.ultimismc.skywars.game.island;

import com.ultimismc.skywars.core.game.features.cosmetics.cages.Cage;
import lombok.Data;
import org.bukkit.Location;

/**
 * @author DirectPlan
 */
@Data
public class Island {

    private final Location cageLocation;
    private boolean taken;
    private Cage cage;
}
