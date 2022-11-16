package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public interface Game {

    GameState getGameState();

    void setGameState(GameState gameState);

    int getPlayersLeft();

    void prepareUser(User user);

    void quitUser(User user);
}
