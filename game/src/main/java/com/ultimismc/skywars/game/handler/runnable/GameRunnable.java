package com.ultimismc.skywars.game.handler.runnable;

import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class GameRunnable implements Runnable {

    private final GameHandler gameHandler;

    @Override
    public void run() {
        long gameTime = gameHandler.getGameTime();
        gameHandler.setGameTime(gameTime += 1000L);


    }
}
