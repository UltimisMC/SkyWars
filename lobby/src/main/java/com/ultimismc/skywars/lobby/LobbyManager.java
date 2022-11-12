package com.ultimismc.skywars.lobby;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.user.UserManager;
import com.ultimismc.skywars.lobby.menu.PurchasesMenu;
import com.ultimismc.skywars.lobby.menu.UserStatsMenu;
import com.ultimismc.skywars.lobby.shop.SkyWarsShopHandler;
import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.CustomLocation;
import xyz.directplan.directlib.StringUtil;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.scoreboard.ScoreboardManager;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LobbyManager {

    private final SkyWarsPlugin plugin;

    private Location spawnLocation;

    private final LobbyScoreboard lobbyScoreboard;
    private final MenuManager menuManager;
    private final UserManager userManager;
    private final SkyWarsShopHandler shopHandler;

    public LobbyManager(SkyWarsPlugin plugin) {
        this.plugin = plugin;

        menuManager = plugin.getMenuManager();
        userManager = plugin.getUserManager();

        shopHandler = new SkyWarsShopHandler(plugin);

        ScoreboardManager scoreboardManager = new ScoreboardManager(plugin, "Ultimis Skywars - Scoreboard Thread");
        lobbyScoreboard = new LobbyScoreboard(scoreboardManager);

        spawnLocation = CustomLocation.stringToLocation(ConfigKeys.SPAWN_LOCATION.getStringValue()).toBukkitLocation();
    }

    public void initializeLobby() {
        plugin.registerListeners(new LobbyListener(this));

        shopHandler.initializeShop(this);
    }

    public void teleportSpawn(Player player) {
        if(spawnLocation == null) return;
        player.teleport(spawnLocation);
    }

    public void handleJoin(User user) {
        Player player = user.getPlayer();
        teleportSpawn(player);
        lobbyScoreboard.updateGameScoreboard(user);

        menuManager.applyDesign(new LobbyPlayerInventoryUi(this, user));
    }

    public void handleQuit(User user) {
        lobbyScoreboard.removeScoreboard(user);


    }

    public void openShopMenu(User user) {
        shopHandler.openShopMenu(user);
    }

    public void openStatsMenu(User user) {
        menuManager.openInventory(user.getPlayer(), new UserStatsMenu(user));
    }

    public void openSoulWellMenu(User user) {
        shopHandler.openSoulWellMenu(user);
    }

    public void openSoulWellMenu(Player player) {
        User user = userManager.getCachedUser(player);
        if(user == null) return;
        openSoulWellMenu(user);
    }

    public void openPurchasesMenu(Player player, User user) {
        menuManager.openInventory(player, new PurchasesMenu(user));
    }

    public void openPurchasesMenu(User user) {
        openPurchasesMenu(user.getPlayer(), user);
    }

    public void setSpawnLocation(Location location) {
        CustomLocation spawnLocation = CustomLocation.fromBukkitLocation(location);
        this.spawnLocation = spawnLocation.toBukkitLocation();
        String serializedSpawn = CustomLocation.locationToString(spawnLocation);

        ConfigKeys.SPAWN_LOCATION.setValue(serializedSpawn);
    }

    public String getProgressBar(int current, int max) {

        int bars = ConfigKeys.PROGRESSBAR_BARS.getInteger();
        String barSymbol = ConfigKeys.PROGRESSBAR_BARS_SYMBOL.getStringValue();
        String stable = ConfigKeys.PROGRESSBAR_COLORS_STABLE.getStringValue();
        String balanced = ConfigKeys.PROGRESSBAR_COLORS_BALANCED.getStringValue();
        String low = ConfigKeys.PROGRESSBAR_COLORS_LOW.getStringValue();
        String uncompleted = ConfigKeys.PROGRESSBAR_COLORS_UNCOMPLETED.getStringValue();

        return StringUtil.getProgressBar(current, max, bars, barSymbol, new String[] {stable, balanced, low, uncompleted});
    }
}
