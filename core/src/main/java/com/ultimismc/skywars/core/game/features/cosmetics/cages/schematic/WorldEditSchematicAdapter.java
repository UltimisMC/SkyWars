package com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.registry.WorldData;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author DirectPlan
 */
@Getter
public class WorldEditSchematicAdapter implements SchematicAdapter {

    private final String name = "WorldEdit";
    private final String fileType = "schematic";

    private final BukkitWorld bukkitWorld;

    private final WorldEditPlugin worldEditPlugin;

    public WorldEditSchematicAdapter(World world) {
        bukkitWorld = new BukkitWorld(world);

        worldEditPlugin = WorldEditPlugin.getPlugin(WorldEditPlugin.class);
    }

    @Override
    public WorldEditCageSchematic loadSchematic(File file) {
        return new WorldEditCageSchematic(bukkitWorld, file);
    }

}
@Getter
class WorldEditCageSchematic implements CageSchematic {

    private final BukkitWorld bukkitWorld;
    private final File schematicFile;
    private Clipboard clipboard;

    public WorldEditCageSchematic(BukkitWorld bukkitWorld, File schematicFile) {
        this.bukkitWorld = bukkitWorld;
        this.schematicFile = schematicFile;
        try {
            ClipboardFormat format = ClipboardFormat.findByFile(schematicFile);
            assert format != null;
            ClipboardReader reader = format.getReader(new FileInputStream(schematicFile));
            clipboard = reader.read(bukkitWorld.getWorldData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void placeSchematic(Location location, boolean ignoreAir) {
        placeClipboard(location, ignoreAir);
    }

    public void placeClipboard(Location location, boolean ignoreAir) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        EditSessionFactory editSessionFactory = WorldEdit.getInstance().getEditSessionFactory();

        EditSession editSession = editSessionFactory.getEditSession(bukkitWorld, -1);
        WorldData worldData = bukkitWorld.getWorldData();
        Operation operation = new ClipboardHolder(clipboard, worldData)
                .createPaste(editSession, worldData)
                .to(Vector.toBlockPoint(x, y, z))
                // configure here
                .ignoreAirBlocks(ignoreAir)
                .build();

        try {
            Operations.complete(operation);
        } catch (WorldEditException e) {
            e.printStackTrace();
        }
    }
}
