package com.ultimismc.skywars.lobby;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.lobby.shop.SkyWarsShopHandler;
import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.CustomLocation;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.scoreboard.ScoreboardManager;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LobbyManager {

    private Location spawnLocation;

    private final LobbyScoreboard lobbyScoreboard;
    private final MenuManager menuManager;
    private final SkyWarsShopHandler shopHandler;

    public LobbyManager(SkyWarsPlugin plugin) {

        menuManager = plugin.getMenuManager();
        shopHandler = new SkyWarsShopHandler(menuManager);

        ScoreboardManager scoreboardManager = new ScoreboardManager(plugin, "Ultimis Skywars - Scoreboard Thread");
        lobbyScoreboard = new LobbyScoreboard(scoreboardManager);

        spawnLocation = CustomLocation.stringToLocation(ConfigKeys.SPAWN_LOCATION.getStringValue()).toBukkitLocation();
    }

    public void initializeLobby() {

        shopHandler.initializeShop();
    }

    public void handleJoin(User user) {
        Player player = user.getPlayer();
        if(spawnLocation != null) {
            player.teleport(spawnLocation);
        }

        lobbyScoreboard.updateGameScoreboard(user);

        menuManager.applyDesign(new LobbyPlayerInventoryUi(this, user));
    }

    public void handleQuit(User user) {
        lobbyScoreboard.removeScoreboard(user);


    }

    public void openShopMenu(User user) {
        shopHandler.openShopMenu(user);
    }

    public void setSpawnLocation(Location location) {
        CustomLocation spawnLocation = CustomLocation.fromBukkitLocation(location);
        this.spawnLocation = spawnLocation.toBukkitLocation();
        String serializedSpawn = CustomLocation.locationToString(spawnLocation);

        ConfigKeys.SPAWN_LOCATION.setValue(serializedSpawn);
    }
}
