package com.ultimismc.skywars.core;

import co.aikar.commands.*;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.ConfigHandler;

/**
 * @author DirectPlan
 */
public class CommandHandler {

    private final BukkitCommandManager commandManager;

    public CommandHandler(SkyWarsPlugin plugin) {
        commandManager = new BukkitCommandManager(plugin);

        ConfigHandler configHandler = plugin.getConfigHandler();
        UserManager userManager = plugin.getUserManager();

        registerDependency(ConfigHandler.class, configHandler);
        registerDependency(UserManager.class, userManager);

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

    public <T> void registerDependency(Class<? extends T> clazz, T t) {
        commandManager.registerDependency(clazz, t);
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
