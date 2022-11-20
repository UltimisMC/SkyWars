package com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic;

import org.bukkit.Location;

import java.io.File;

/**
 * @author DirectPlan
 */
public interface CageSchematic {

    File getSchematicFile();

    void placeSchematic(Location location, boolean ignoreAir);
}
