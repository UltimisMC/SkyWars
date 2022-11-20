package com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic;

import com.ultimismc.skywars.core.user.User;

import java.io.File;

/**
 * @author DirectPlan
 */
public interface SchematicAdapter {

    String getName();

    String getFileType();

    CageSchematic loadSchematic(File file);

    void saveSchematic(File file, CageSchematic schematic);

    CageSchematic getSchematicFromSelection(User user);
}
