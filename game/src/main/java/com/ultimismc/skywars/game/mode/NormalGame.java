package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.AbstractGame;
import com.ultimismc.skywars.game.handler.GameHandler;

/**
 * @author DirectPlan
 */
public class NormalGame extends AbstractGame {

    public NormalGame(GameHandler gameHandler) {
        super(gameHandler, new NormalChestRegistry());
    }

    @Override
    public void prepareUser(User user) {
        super.prepareUser(user);

    }
}
