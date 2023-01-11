package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.game.handler.Game;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;

/**
 * @author DirectPlan
 */
public class NormalGame extends Game {

    public NormalGame(SkyWarsPlugin plugin, GameHandler gameHandler) {
        super(plugin, gameHandler, new NormalChestRegistry());
    }

    @Override
    public void prepareUser(UserGameSession user) {
        super.prepareUser(user);

    }
}
