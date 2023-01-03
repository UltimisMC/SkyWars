package com.ultimismc.skywars.core.game.menu;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.server.menu.ServerListMenu;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.manager.MenuManager;

/**
 * @author DirectPlan
 */
public class GameMenuHandler {

    private final SkyWarsPlugin plugin;
    private final MenuManager menuManager;
    private final SkyWarsServerManager serverManager;

    public GameMenuHandler(SkyWarsPlugin plugin) {
        this.plugin = plugin;

        this.menuManager = plugin.getMenuManager();
        this.serverManager = plugin.getServerManager();
    }

    public void openMapSelector(User user, TeamType teamType, GameType gameType) {
        menuManager.openInventory(user.getPlayer(), new MapSelectorMenu(serverManager, user, teamType, gameType));
    }

    public void openSkyWarsPlayMenu(User user, TeamType teamType) {
        menuManager.openInventory(user.getPlayer(), new SkyWarsPlayMenu(this, serverManager, user, teamType));
    }

    public void openConnectedServersMenu(Player player) {
        menuManager.openInventory(player, new ServerListMenu(plugin, player));
    }
}
