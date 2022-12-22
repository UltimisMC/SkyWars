package com.ultimismc.skywars.game;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.Map;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserPlayerInventoryUi;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserSessionHandler;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.CustomLocation;
import xyz.directplan.directlib.config.ConfigEntry;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.scoreboard.ScoreboardManager;

import java.util.UUID;

/**
 * @author DirectPlan
 */
@Getter
public class GameManager implements FeatureInitializer {

    private final SkyWarsPlugin plugin;
    private final MenuManager menuManager;

    private GameHandler gameHandler;
    private GameConfig gameConfig;
    private final ScoreboardManager scoreboardManager;

    private final UserSessionHandler userSessionHandler;

    private Location spawnLocation;

    public GameManager(SkyWarsPlugin plugin) {
        this.plugin = plugin;

        menuManager = plugin.getMenuManager();

        scoreboardManager = new ScoreboardManager(plugin, "Ultimis SkyWars Scoreboard");
        userSessionHandler = new UserSessionHandler(gameConfig);
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        FeatureHandler featureHandler = plugin.getFeatureHandler();
        spawnLocation = CustomLocation.stringToLocation(ConfigKeys.SPAWN_LOCATION.getStringValue()).toBukkitLocation();

        gameHandler = new GameHandler(plugin, this);
        featureHandler.initializeFeature(gameHandler);

        gameConfig = gameHandler.getGameConfig();
    }

    public void shutdown() {
        gameHandler.shutdown();
    }

    public void handleJoin(User user) {
        if(gameConfig.isSetupMode()) {
            teleportWaitingLocation(user);
            user.sendMessage("&b&lSETUP MODE:");
            user.sendMessage("&7This server is under setup mode. Only thing that's functional is the commands.");
            user.sendMessage("&7You've been teleported to waiting spawn!");
            return;
        }
        gameHandler.prepareUser(user);
    }

    public void handleQuit(User user) {


        gameHandler.quitUser(user);
    }

    public void teleportWaitingLocation(User user) {
        user.teleport(spawnLocation);
    }

    public void setWaitingSpawnLocation(Location location) {
        this.spawnLocation = location;
        saveSpawnLocation(location, ConfigKeys.SPAWN_LOCATION);
    }

    private void saveSpawnLocation(Location location, ConfigEntry configEntry) {
        CustomLocation spawnLocation = CustomLocation.fromBukkitLocation(location);
        String serializedSpawn = CustomLocation.locationToString(spawnLocation);

        configEntry.setValue(serializedSpawn);
    }

    public void removeScoreboard(UUID uuid) {
        scoreboardManager.removeScoreboard(uuid);
    }

    public void openMenu(Player player, InventoryUI inventoryUI) {
        menuManager.openInventory(player, inventoryUI);
    }

    public void applyPlayerMenu(UserPlayerInventoryUi playerInventoryUi) {
        menuManager.applyDesign(playerInventoryUi);
    }

    public String getServerName() {
        return gameConfig.getName();
    }

    public Map getServerMap() {
        return gameConfig.getMap();
    }

    @Override
    public String getName() {
        return "Game Manager";
    }
}
