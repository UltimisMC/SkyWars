package com.ultimismc.skywars.game.chest;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.game.config.MapConfigKeys;
import com.ultimismc.skywars.game.events.SkyWarsEventHandler;
import com.ultimismc.skywars.game.events.SkyWarsEventUpdater;
import com.ultimismc.skywars.game.handler.Game;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.CustomLocation;
import xyz.directplan.directlib.PluginUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class ChestHandler implements FeatureInitializer {

    @Getter private final String name = "Chest Handler";
    @Getter private final Map<Location, Chest> chests = new HashMap<>();

    private final GameHandler gameHandler;
    private final SkyWarsEventHandler skyWarsEventHandler;

    public ChestHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        skyWarsEventHandler = gameHandler.getSkyWarsEventHandler();

        long THREE_MINUTES = (60000 * 3);
        skyWarsEventHandler.addEvent(new ChestRefillSkyWarsEvent(RefillPhase.THIRD, THREE_MINUTES));
        skyWarsEventHandler.addEvent(new ChestRefillSkyWarsEvent(RefillPhase.SECOND, THREE_MINUTES));
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        List<String> serializedChests = MapConfigKeys.MAP_SERIALIZED_CHESTS.getStringList();
        World gameWorld = gameHandler.getGameWorld();

        // Short-term solution for serializing objects. Will be improved in the future.
        for(String serializedChest : serializedChests) {
            if(serializedChest.isEmpty()) continue;
            String[] args = serializedChest.split("/");
            String serializedLocation = args[0];
            CustomLocation customLocation = CustomLocation.stringToLocation(serializedLocation);
            boolean midChest = Boolean.parseBoolean(args[1]);

            Block block = gameWorld.getBlockAt(customLocation.toBukkitLocation());
            if(block.getType() != Material.CHEST) continue;
            addChest(block, midChest);
        }

        plugin.registerListeners(new ChestListener(gameHandler));
    }

    @Override
    public void shutdownFeature(SkyWarsPlugin plugin) {
        List<String> serializedChests = new ArrayList<>();

        for(Chest chest : chests.values()) {
            Location location = chest.getLocation();
            String serializedLocation = CustomLocation.locationToString(location);
            boolean midChest = chest.isMidChest();
            serializedChests.add(serializedLocation + "/" + midChest);

            // Destroying hologram
            ChestHologram hologram = chest.getChestHologram();
            hologram.destroy();
        }
        MapConfigKeys.MAP_SERIALIZED_CHESTS.setValue(serializedChests);
    }

    public void refillChest(RefillPhase refillPhase, Chest chest) {
        Game game = gameHandler.getGame();
        GameChestRegistry chestRegistry = game.getChestRegistry();
        chestRegistry.refillChest(refillPhase, chest);

        if(!chest.isOpened()) return;
        chest.setOpened(false);
        PluginUtility.playChestAction(chest.getBlockChest(), false);

        resetChest(chest);
    }

    public void refillAllChests(RefillPhase refillPhase) {
        chests.forEach((location, chest) -> refillChest(refillPhase, chest));
    }

    public void refillAllChests() {
        refillAllChests(RefillPhase.FIRST);
    }

    public void openChest(UserGameSession userGameSession, Block block) {
        Chest chest = getChest(block);
        if(chest == null) return;
        Player player = userGameSession.getPlayer();
        player.openInventory(chest.getInventory());

        if(chest.isOpened()) return;
        chest.setOpened(true);
        PluginUtility.playChestAction(chest.getBlockChest(), true);

        userGameSession.increaseChestsOpened();

        ChestHologramUpdater updater = new ChestHologramUpdater(chest);
        skyWarsEventHandler.addUpdater(updater);
    }
    public Chest addChest(Chest chest) {
        chests.put(chest.getLocation(), chest);
        return chest;
    }

    public Chest addChest(Block block, boolean midChest) {
        return addChest(new Chest(block, midChest));
    }

    public Chest getChest(Location location) {
        return chests.get(location);
    }

    public Chest getChest(Block block) {
        Location location = block.getLocation();
        return getChest(location);
    }

    public void removeChest(Location location) {
        Chest chest = chests.remove(location);
        resetChest(chest);
    }

    public void resetChest(Chest chest) {
        SkyWarsEventUpdater updater = chest.getUpdater();
        skyWarsEventHandler.removeUpdater(updater);

        chest.destroyHologram();
    }

    public void removeChest(Block block) {
        Location location = block.getLocation();
        removeChest(location);
    }

    public int getSize() {
        return chests.size();
    }
}
