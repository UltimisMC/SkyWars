package com.ultimismc.skywars.core.server.menu;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameState;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.server.SkyWarsServer;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitTask;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PaginatedMenu;
import xyz.directplan.directlib.inventory.PaginatedModel;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author DirectPlan
 */

public class ServerListMenu extends PaginatedMenu<SkyWarsServer> {

    private final SkyWarsServerManager serverManager;

    private final SkyWarsPlugin plugin;
    private BukkitTask bukkitTask;

    public ServerListMenu(SkyWarsPlugin plugin) {
        super("SkyWars Servers", 6, PaginatedModel.HYPIXEL_MODEL);

        this.plugin = plugin;
        this.serverManager = plugin.getServerManager();
    }

    @Override
    public Collection<SkyWarsServer> getList() {
        return serverManager.getServers().stream()
                .sorted((o1, o2) -> Integer.compare(o2.getOnlinePlayers(), o1.getMaximumPlayers()))
                .collect(Collectors.toList());
    }

    @Override
    public void build(Player player) {
        super.build(player);

        bukkitTask = Bukkit.getScheduler()
                .runTaskTimer(plugin, new ServerListMenuUpdater(player, this), 20, 20);
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

        GameState gameState = server.getState();
        String displayName = server.getDisplayName();
        String serverId = server.getId();
        int onlinePlayers = server.getOnlinePlayers();
        int maximumPlayers = server.getMaximumPlayers();

        boolean lobby = server.isLobby();
        MenuItem menuItem = new MenuItem(Material.INK_SACK, statusColor + displayName, statusDurability);
        menuItem.setLore("&8" + serverId);
        if(lobby) menuItem.setLore("&7&oThis is a lobby server!");
        menuItem.setLore(" ");
        if(!server.isLobby()) {
            menuItem.setLore("&fServer State: " + statusColor + gameState.name());
        }
        menuItem.setLore("&fPlayers: &a" + onlinePlayers + "/" + maximumPlayers);
        menuItem.setLore(" ");
        if(!server.isLobby()) {
            GameType gameType = server.getGameType();
            TeamType teamType = server.getTeamType();
            menuItem.setLore("&fGame Type: &a" + gameType.getDisplayName());
            menuItem.setLore("&fTeam Type: &a" + teamType.getName() + " &7(Max Players: " + teamType.getMaximumPlayers() + ". Max Teams: " + teamType.getMaximumTeam() + ")");
            menuItem.setLore(" ");
        }
        menuItem.setLore("&eClick to jump to &a"+serverId+"&e!");
        return menuItem;
    }

    @Override
    public void onClose(Player player, Inventory inventory) {
        bukkitTask.cancel();
    }
}
