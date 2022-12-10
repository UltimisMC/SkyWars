package com.ultimismc.skywars.core;

import co.aikar.commands.*;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.ConfigHandler;
import xyz.directplan.directlib.inventory.manager.MenuManager;

import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
public class CommandHandler {

    private final BukkitCommandManager commandManager;

    public CommandHandler(SkyWarsPlugin plugin) {
        commandManager = new BukkitCommandManager(plugin);

        ConfigHandler configHandler = plugin.getConfigHandler();
        UserManager userManager = plugin.getUserManager();
        MenuManager menuManager = plugin.getMenuManager();
        FeatureHandler featureHandler = plugin.getFeatureHandler();

        registerDependency(ConfigHandler.class, configHandler);
        registerDependency(UserManager.class, userManager);
        registerDependency(MenuManager.class, menuManager);
        registerDependency(FeatureHandler.class, featureHandler);

        commandManager.enableUnstableAPI("help");

        commandManager.setFormat(MessageType.HELP, ChatColor.AQUA, ChatColor.DARK_AQUA, ChatColor.WHITE);
        commandManager.setFormat(MessageType.SYNTAX, ChatColor.AQUA, ChatColor.DARK_AQUA, ChatColor.WHITE);

        CommandContexts<BukkitCommandExecutionContext> commandContexts = commandManager.getCommandContexts();

        commandContexts.registerContext(Material.class, resolver -> {
            String name = resolver.popFirstArg();
            Material material;
            try {
                material = Material.valueOf(name.toUpperCase());
            }catch (Exception ignored) {
                throw new InvalidCommandArgument("&cInvalid material '" + name + "'");
            }
            return material;
        });

        commandContexts.registerContext(GameType.class, resolver -> {
            String name = resolver.popFirstArg();
            GameType gameType;
            try {
                gameType = GameType.valueOf(name.toUpperCase());
            }catch (Exception ignored) {
                throw new InvalidCommandArgument("&cInvalid game type '" + name + "'");
            }
            return gameType;
        });

        commandContexts.registerContext(TeamType.class, resolver -> {
            String name = resolver.popFirstArg();
            TeamType teamType;
            try {
                teamType = TeamType.valueOf(name.toUpperCase());
            }catch (Exception ignored) {
                throw new InvalidCommandArgument("&cInvalid team type '" + name + "'");
            }
            return teamType;
        });

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
