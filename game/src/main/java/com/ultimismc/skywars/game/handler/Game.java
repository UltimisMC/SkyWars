package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.game.chest.GameChestRegistry;
import com.ultimismc.skywars.game.user.UserGameSession;

import java.util.LinkedList;
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

    void prepareUser(UserGameSession user);

    void quitUser(UserGameSession user);

    GameState getGameState();

    void setGameState(GameState gameState);

    List<UserGameSession> getSpectators();

    LinkedList<UserGameSession> getPlayers();

    void addSpectator(UserGameSession user);

    void removeSpectator(UserGameSession user);

    boolean isWaiting();

    boolean isStarting();

    boolean hasStarted();

    boolean hasEnded();

    boolean isRestarting();

    int getMinimumPlayers();
}
