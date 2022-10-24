package com.ultimismc.skywars.lobby.commands;

import co.aikar.commands.*;
import com.ultimismc.skywars.lobby.LobbyManager;
import com.ultimismc.skywars.lobby.SkyWarsLobbyPlugin;
import com.ultimismc.skywars.lobby.user.User;
import com.ultimismc.skywars.lobby.user.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.ConfigHandler;

/**
 * @author DirectPlan
 */
public class CommandHandler {

    private final BukkitCommandManager commandManager;

    public CommandHandler(SkyWarsLobbyPlugin plugin) {
        commandManager = new BukkitCommandManager(plugin);

        ConfigHandler configHandler = plugin.getConfigHandler();
        UserManager userManager = plugin.getUserManager();
        LobbyManager lobbyManager = plugin.getLobbyManager();

        commandManager.registerDependency(ConfigHandler.class, configHandler);
        commandManager.registerDependency(LobbyManager.class, lobbyManager);
        commandManager.registerDependency(UserManager.class, userManager);

        commandManager.enableUnstableAPI("help");

        commandManager.setFormat(MessageType.HELP, ChatColor.AQUA, ChatColor.DARK_AQUA, ChatColor.WHITE);
        commandManager.setFormat(MessageType.SYNTAX, ChatColor.AQUA, ChatColor.DARK_AQUA, ChatColor.WHITE);

        CommandContexts<BukkitCommandExecutionContext> commandContexts = commandManager.getCommandContexts();

        commandContexts.registerIssuerAwareContext(User.class, resolver -> {
            BukkitCommandIssuer commandIssuer = resolver.getIssuer();
            if(resolver.hasFlag("other")) {
                String name = resolver.popFirstArg();
                Player player = ACFBukkitUtil.findPlayerSmart(commandIssuer, name);
                if(player == null) throw new ShowCommandHelp();
                return userManager.getCachedUser(player);
            }
            if(!commandIssuer.isPlayer()) {
                throw new InvalidCommandArgument(MessageKeys.NOT_ALLOWED_ON_CONSOLE);
            }
            Player player = commandIssuer.getPlayer();
            return userManager.getCachedUser(player);
        });
    }

    public void registerCommand(BaseCommand command) {
        commandManager.registerCommand(command);
    }

    public void registerCommands(BaseCommand... commands) {
        for(BaseCommand command : commands) {
            registerCommand(command);
        }
    }
}
