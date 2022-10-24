package com.ultimismc.skywars.lobby;

import com.ultimismc.skywars.lobby.commands.CommandHandler;
import com.ultimismc.skywars.lobby.commands.ShopCommand;
import com.ultimismc.skywars.lobby.commands.UltimisSkyWarsCommand;
import com.ultimismc.skywars.lobby.config.ConfigKeys;
import com.ultimismc.skywars.lobby.config.MessageConfigKeys;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.storage.UserStorage;
import com.ultimismc.skywars.lobby.user.UserListener;
import com.ultimismc.skywars.lobby.user.UserManager;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.directplan.directlib.config.BukkitConfigHandler;
import xyz.directplan.directlib.config.ConfigHandler;
import xyz.directplan.directlib.inventory.MenuListener;
import xyz.directplan.directlib.inventory.manager.MenuManager;

/**
 * @author DirectPlan
 */
@Getter
public class SkyWarsLobbyPlugin extends JavaPlugin {


    private ConfigHandler configHandler;
    private UserStorage storage;
    private UserManager userManager;
    private MenuManager menuManager;

    private LobbyManager lobbyManager;
    private CommandHandler commandHandler;

    @Override
    public void onEnable() {
        configHandler = new BukkitConfigHandler(this);
        configHandler.loadConfiguration("config.yml", ConfigKeys.class);
        configHandler.loadConfiguration("messages.yml", MessageConfigKeys.class);
        configHandler.loadConfiguration("shop-messages.yml", ShopMessageKeys.class);

        storage = new UserStorage(this);
        storage.connect();

        userManager = new UserManager(this);
        menuManager = new MenuManager();
        lobbyManager = new LobbyManager(this);
        commandHandler = new CommandHandler(this);

        lobbyManager.initializeLobby();

        commandHandler.registerCommands(new UltimisSkyWarsCommand(), new ShopCommand());
        registerListeners(new MenuListener(menuManager), new UserListener(lobbyManager, userManager));
    }


    @Override
    public void onDisable() {

        configHandler.saveConfigurations();
        userManager.saveUsers();

        storage.close();
    }

    private void registerListeners(Listener... listeners) {
        PluginManager pluginManager = getServer().getPluginManager();
        for(Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }
}
