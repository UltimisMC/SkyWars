package com.ultimismc.skywars.game;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.game.commands.SkyWarsGameCommand;
import com.ultimismc.skywars.game.commands.SkyWarsSetupCommand;
import com.ultimismc.skywars.game.config.GameConfigKeys;
import com.ultimismc.skywars.game.config.MapConfigKeys;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.user.GameUserLoadedListener;
import com.ultimismc.skywars.game.user.GameUserSavedListener;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class GameSkyWarsPlugin extends SkyWarsPlugin {

    private GameManager gameManager;

    @Override
    public void enable() {
        configHandler.loadConfiguration("messages.yml", MessageConfigKeys.class);
        configHandler.loadConfiguration("game.yml", GameConfigKeys.class);
        configHandler.loadConfiguration("map.yml", MapConfigKeys.class);

        featureHandler.initializeFeature(gameManager = new GameManager(this));

        registerListeners(new SkyWarsGameListener(gameManager.getGameHandler()));

        commandHandler.registerCommands(new SkyWarsGameCommand(), new SkyWarsSetupCommand());
        userListener.setUserLoadedListener(new GameUserLoadedListener(this, gameManager));
        userListener.setUserSavedListener(new GameUserSavedListener(gameManager));
    }

    @Override
    public void disable() {
        gameManager.shutdown();
    }
}
