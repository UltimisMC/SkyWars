package com.ultimismc.skywars.game.user;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserLoadedListener;
import com.ultimismc.skywars.game.GameManager;

/**
 * @author DirectPlan
 */
public class GameUserLoadedListener extends UserLoadedListener {

    private final GameManager gameManager;
    public GameUserLoadedListener(SkyWarsPlugin plugin, GameManager gameManager) {
        super(plugin);

        this.gameManager = gameManager;
    }

    @Override
    public void onUserLoaded(User user) {
        super.onUserLoaded(user);

        gameManager.handleJoin(user);
    }
}
