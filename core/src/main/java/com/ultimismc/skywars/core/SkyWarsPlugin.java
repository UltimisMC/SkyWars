package com.ultimismc.skywars.core;

import com.ultimismc.skywars.core.commands.CageCommand;
import com.ultimismc.skywars.core.commands.SkyWarsDebugCommand;
import com.ultimismc.skywars.core.config.CageConfigKeys;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameListener;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.placeholders.PlaceholderExpansionHandler;
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
    protected PlaceholderExpansionHandler placeholderExpansionHandler;
    protected GameConfig gameConfig;

    private final ServerInitializer serverInitializer;

    public SkyWarsPlugin(ServerInitializer serverInitializer) {
        this.serverInitializer = serverInitializer;
    }

    public abstract void enable();

    public abstract void disable();

    @Override
    public void onEnable() {
        libraryLoader = new LibraryLoader(this);
        libraryLoader.loadDependencies();

        configHandler = new BukkitConfigHandler(this);
        configHandler.loadConfiguration("config.yml", ConfigKeys.class);
        configHandler.loadConfiguration("cages.yml", CageConfigKeys.class);

        storage = new UserStorage(this);
        storage.connect();

        gameConfig = serverInitializer.loadGameConfig(this);
        serverManager = new SkyWarsServerManager(this, gameConfig);
        serverManager.connect();

        userManager = new UserManager(this);
        menuManager = new MenuManager();
        rankManager = new RankManager();
        featureHandler = new FeatureHandler(this);
        commandHandler = new CommandHandler(this);

        placeholderExpansionHandler = new PlaceholderExpansionHandler();

        featureHandler.initializeFeatures();

        placeholderExpansionHandler.registerPlaceholderExpansion(new SkyWarsPlaceholderExpansion(this));
        registerListeners(new MenuListener(menuManager),
                new GameListener(userManager, featureHandler),
                userListener = new UserListener(userManager));

        commandHandler.registerCommands(new SkyWarsDebugCommand(), new CageCommand());
        enable();
    }

    @Override
    public void onDisable() {
        disable();
        serverInitializer.saveGameConfig(this);

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

    public void log(String message) {
        getLogger().log(Level.INFO, message);
    }

    public void shutdown(String reason) {
        getLogger().severe("Shutting down server: " + reason);
        this.getServer().getPluginManager().disablePlugin(this);
    }
}
