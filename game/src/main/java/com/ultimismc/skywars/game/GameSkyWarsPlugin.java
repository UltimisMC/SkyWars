package com.ultimismc.skywars.game;

import com.ultimismc.skywars.core.SkyWarsPlugin;
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
        gameManager = new GameManager(this);
        gameManager.initialize();

        userListener.setUserLoadedListener(new GameUserLoadedListener(this, gameManager));
        userListener.setUserSavedListener(new GameUserSavedListener(gameManager));
    }

    @Override
    public void disable() {
        gameManager.shutdown();
    }
}
