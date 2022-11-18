package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.AbstractGame;
import com.ultimismc.skywars.game.handler.GameHandler;

/**
 * @author DirectPlan
 */
public class InsaneGame extends AbstractGame {

    public InsaneGame(GameHandler gameHandler) {
        super(gameHandler, new InsaneChestRegistry());
    }

    @Override
    public void prepareUser(User user) {
        super.prepareUser(user);

    }
}
