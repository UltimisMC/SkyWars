package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.user.User;

import java.util.List;

/**
 * @author DirectPlan
 */
public interface Game {

    void startGame();

    void endGame();

    void prepareUser(User user);

    void quitUser(User user);

    GameState getGameState();

    void setGameState(GameState gameState);

    List<User> getSpectators();

    List<User> getPlayers();

    void addSpectator(User user);

    void removeSpectator(User user);

    boolean isWaiting();

    boolean isStarting();

    boolean hasStarted();

    int getMinimumPlayers();
}
