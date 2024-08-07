package com.ultimismc.skywars.core;

import com.ultimismc.skywars.core.commands.CageCommand;
import com.ultimismc.skywars.core.commands.PlayCommand;
import com.ultimismc.skywars.core.commands.SkyWarsDebugCommand;
import com.ultimismc.skywars.core.commands.SkyWarsPlayCommand;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.events.SkyWarsEvent;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameListener;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.menu.GameMenuHandler;
import com.ultimismc.skywars.core.placeholders.SkyWarsPlaceholderExpansion;
import com.ultimismc.skywars.core.rank.RankManager;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.storage.UserStorage;
import com.ultimismc.skywars.core.user.UserListener;
import com.ultimismc.skywars.core.user.UserManager;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.directplan.directlib.config.BukkitConfigHandler;
import xyz.directplan.directlib.config.ConfigHandler;
import xyz.directplan.directlib.inventory.MenuListener;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.library.LibraryLoader;

import java.util.logging.Level;

/**
 * @author DirectPlan
 */
@Getter
public abstract class SkyWarsPlugin extends JavaPlugin {

    protected ConfigHandler configHandler;
    protected UserStorage storage;
    protected SkyWarsServerManager serverManager;
    protected UserManager userManager;
    protected MenuManager menuManager;
    protected RankManager rankManager;
    protected LibraryLoader libraryLoader;

    protected CommandHandler commandHandler;
    protected UserListener userListener;
    protected FeatureHandler featureHandler;
    protected GameConfig gameConfig;
    protected GameMenuHandler gameMenuHandler;

    private final SkyWarsConfigManager skyWarsConfigManager;

    public SkyWarsPlugin(SkyWarsConfigManager skyWarsConfigManager) {
        this.skyWarsConfigManager = skyWarsConfigManager;
    }

    public abstract void enable();

    public abstract void disable();

    @Override
    public void onEnable() {
        libraryLoader = new LibraryLoader(this);
        libraryLoader.loadDependencies();

        configHandler = new BukkitConfigHandler(this);
        configHandler.loadConfiguration("config.yml", ConfigKeys.class);

        storage = new UserStorage(this);
        storage.connect();

        gameConfig = skyWarsConfigManager.loadGameConfig(this);
        serverManager = new SkyWarsServerManager(this, gameConfig);
        serverManager.connect();

        userManager = new UserManager(this);
        menuManager = new MenuManager();
        rankManager = new RankManager();
        featureHandler = new FeatureHandler(this);
        gameMenuHandler = new GameMenuHandler(this);
        commandHandler = new CommandHandler(this);


        featureHandler.initializeFeatures();

        new SkyWarsPlaceholderExpansion(this).register();

        registerListeners(new MenuListener(menuManager, player -> userManager.getCachedUser(player)),
                new GameListener(userManager),
                userListener = new UserListener(userManager));

        commandHandler.registerCommands(new SkyWarsDebugCommand(),
                new CageCommand(),
                new PlayCommand(),
                new SkyWarsPlayCommand());

        enable();
    }

    @Override
    public void onDisable() {
        disable();
        skyWarsConfigManager.saveGameConfig(this);

        featureHandler.shutdownFeatures();
        configHandler.saveConfigurations();
        userManager.saveUsers();

        serverManager.close();
        storage.close();
    }

    public void registerListeners(Listener... listeners) {
        PluginManager pluginManager = getServer().getPluginManager();
        for(Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }

    public void callEvent(SkyWarsEvent skyWarsEvent) {
        getServer().getPluginManager().callEvent(skyWarsEvent);
    }

    public void log(String message) {
        getLogger().log(Level.INFO, message);
    }

    public void disablePlugin(String reason) {
        getLogger().severe("Disabling plugin: " + reason);
        getServer().getPluginManager().disablePlugin(this);
    }

    public void shutdownServer(String reason) {
        getLogger().severe("Shutting down server: " + reason);
        getServer().shutdown();
    }
}
