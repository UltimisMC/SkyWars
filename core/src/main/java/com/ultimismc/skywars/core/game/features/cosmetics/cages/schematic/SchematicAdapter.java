package com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic;

import java.io.File;

/**
 * @author DirectPlan
 */
public interface SchematicAdapter {

    String getName();

    String getFileType();

    CageSchematic loadSchematic(File file);
}
