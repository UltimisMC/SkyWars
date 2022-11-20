package com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.*;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.*;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.session.SessionManager;
import com.sk89q.worldedit.world.registry.WorldData;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author DirectPlan
 */
@Getter
public class WorldEditSchematicAdapter implements SchematicAdapter {

    private final String name = "WorldEdit";
    private final String fileType = "schematic";

    private final WorldData worldData;

    private final WorldEditPlugin worldEditPlugin;

    public WorldEditSchematicAdapter(World world) {
        BukkitWorld bukkitWorld = new BukkitWorld(world);
        this.worldData = bukkitWorld.getWorldData();

        worldEditPlugin = WorldEditPlugin.getPlugin(WorldEditPlugin.class);
    }

    @Override
    public WorldEditCageSchematic loadSchematic(File file) {
        return new WorldEditCageSchematic(worldData, file);
    }

    @Override
    public void saveSchematic(File file, CageSchematic schematic) {
        schematic.saveSchematic(file);
    }

    @Override
    public CageSchematic getSchematicFromSelection(User user) {
        Player player = user.getPlayer();
        LocalSession localSession = getLocalSession(player);
        ClipboardHolder clipboard; // declare variable
        try {
            clipboard = localSession.getClipboard();
        } catch (EmptyClipboardException ex) {
            return null;
        }
        /* you can now paste the clipboard somewhere, save it to a schematic, etc. */

// bonus example: applying rotation to the player's clipboard
        AffineTransform transform = new AffineTransform();
        clipboard.setTransform(clipboard.getTransform().combine(transform.rotateY(90)));
        return new WorldEditCageSchematic(worldData, clipboard.getClipboard());
    }

    private LocalSession getLocalSession(Player player) {
        com.sk89q.worldedit.extension.platform.Actor actor = new BukkitPlayer(worldEditPlugin, null, player); // WorldEdit's native Player class extends Actor
        SessionManager manager = WorldEdit.getInstance().getSessionManager();
        return manager.getIfPresent(actor);
    }
}
@Getter
class WorldEditCageSchematic implements CageSchematic {

    private final WorldData worldData;
    private File schematicFile;
    private Clipboard clipboard;

    public WorldEditCageSchematic(WorldData worldData, Clipboard clipboard) {
        this.worldData = worldData;
        this.clipboard = clipboard;
    }

    public WorldEditCageSchematic(WorldData worldData, File schematicFile) {
        this.worldData = worldData;
        this.schematicFile = schematicFile;
        try {
            ClipboardFormat format = ClipboardFormat.findByFile(schematicFile);
            assert format != null;
            ClipboardReader reader = format.getReader(new FileInputStream(schematicFile));
            clipboard = reader.read(worldData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void placeSchematic(Location location) {
        placeClipboard(clipboard, location);
    }

    @Override
    public void saveSchematic(File file) {
        try (ClipboardWriter writer = ClipboardFormat.SCHEMATIC.getWriter(new FileOutputStream(file))) {
            writer.write(clipboard, worldData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void placeClipboard(Clipboard clipboard, Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        World world = location.getWorld();

        EditSessionFactory editSessionFactory = WorldEdit.getInstance().getEditSessionFactory();

        BukkitWorld bukkitWorld = new BukkitWorld(world);
        EditSession editSession = editSessionFactory.getEditSession(bukkitWorld, -1);
        WorldData worldData = bukkitWorld.getWorldData();
        Operation operation = new ClipboardHolder(clipboard, worldData)
                .createPaste(editSession, worldData)
                .to(Vector.toBlockPoint(x, y, z))
                // configure here
                .ignoreAirBlocks(true)
                .build();

        try {
            Operations.complete(operation);
        } catch (WorldEditException e) {
            e.printStackTrace();
        }
    }
}
