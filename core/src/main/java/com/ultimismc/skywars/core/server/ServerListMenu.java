package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.ServerState;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PaginatedMenu;
import xyz.directplan.directlib.inventory.PaginatedModel;

import java.util.Collection;

/**
 * @author DirectPlan
 */

public class ServerListMenu extends PaginatedMenu<SkyWarsServer> {

    private final SkyWarsServerManager serverManager;

    public ServerListMenu(SkyWarsServerManager serverManager) {
        super("SkyWars Servers", 6, PaginatedModel.HYPIXEL_MODEL);

        this.serverManager = serverManager;
    }

    @Override
    public Collection<SkyWarsServer> getList() {
        return serverManager.getServers();
    }

    @Override
    public MenuItem buildContent(Player player, SkyWarsServer server) {

        ChatColor statusColor = ChatColor.GRAY;
        int statusDurability = 8; // Game Idling
        if(server.isStarting()) {
            statusDurability = 11; // Game starting
            statusColor = ChatColor.YELLOW;
        }
        if(server.hasStarted()) {
            statusDurability = 10; // Game Started
            statusColor = ChatColor.GREEN;
        }

        ServerState serverState = server.getState();
        String displayName = server.getDisplayName();
        String serverId = server.getId();
        int onlinePlayers = server.getOnlinePlayers();
        int maximumPlayers = server.getMaximumPlayers();
        MenuItem menuItem = new MenuItem(Material.INK_SACK, statusColor + displayName, statusDurability);
        menuItem.setLore("&8" + serverId);
        menuItem.setLore(" ");
        menuItem.setLore("&7Server State: " + statusColor + serverState.name());
        menuItem.setLore("&fPlayers: &a" + onlinePlayers + "/" + maximumPlayers);
        menuItem.setLore(" ");
        menuItem.setLore("&eClick to jump to &a"+serverId+"&e!");
        return menuItem;
    }
}
