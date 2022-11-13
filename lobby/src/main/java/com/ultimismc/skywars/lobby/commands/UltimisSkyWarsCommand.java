package com.ultimismc.skywars.lobby.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.LobbyManager;
import com.ultimismc.skywars.lobby.LobbySkyWarsPlugin;
import com.ultimismc.skywars.lobby.shop.soulwell.roll.SoulWellRollMenu;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.config.ConfigHandler;
import xyz.directplan.directlib.inventory.manager.MenuManager;

/**
 * @author DirectPlan
 */
@CommandAlias("ultimisskywars|uskywars")
@CommandPermission("ultimismc.skywars.admin")
public class UltimisSkyWarsCommand extends BaseCommand {

    @Dependency
    private LobbySkyWarsPlugin plugin;

    @Dependency
    private ConfigHandler configHandler;

    @Dependency
    private LobbyManager lobbyManager;

    @Dependency
    private MenuManager menuManager;

    @Default
    @Description("Shows this help message")
    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("setspawn")
    @Description("Sets the spawn location of the server")
    public void onSetSpawn(Player player) {
        Location location = player.getLocation();
        lobbyManager.setSpawnLocation(location);

        player.sendMessage(PluginUtility.translateMessage("&aYou've identified the new spawn location!"));
    }

    @Subcommand("reload")
    @Description("Reloads all the plugin's YAML files")
    public void onReload(CommandSender sender) {
        configHandler.reloadConfigurations();
        sender.sendMessage(PluginUtility.translateMessage("&b&l(!) &bUltimis SkyWars Lobby &fv&b"+plugin.getDescription().getVersion()+"&f has successfully reloaded!"));
    }

    @Subcommand("rollsoulwell")
    @Description("Rolls soul well...")
    public void onRollSoulWell(User user) {
        menuManager.openInventory(user.getPlayer(), new SoulWellRollMenu(plugin, user));
    }
}
