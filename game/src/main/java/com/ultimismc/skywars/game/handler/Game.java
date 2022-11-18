package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.chest.GameChestRegistry;

import java.util.List;

/**
 * @author DirectPlan
 */
public interface Game {

    // I'm starting to believe that All the methods down here are useless and can be
    // put on the GameHandler only

    GameChestRegistry getChestRegistry();

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
