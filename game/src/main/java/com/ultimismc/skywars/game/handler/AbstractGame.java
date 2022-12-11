package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.game.chest.GameChestRegistry;
import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
@Setter
public abstract class AbstractGame implements Game {

    protected final GameHandler gameHandler;
    private final GameChestRegistry chestRegistry;

    private final int minimumPlayers = 8;
    private GameState gameState = GameState.WAITING;
    private final LinkedList<UserGameSession> players = new LinkedList<>();
    private final LinkedList<UserGameSession> spectators = new LinkedList<>();


    @Override
    public void startGame() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void prepareUser(UserGameSession user) {
        players.add(user);
    }

    @Override
    public void quitUser(UserGameSession user) {
        players.remove(user);
        removeSpectator(user);
    }

    @Override
    public void addSpectator(UserGameSession user) {
        spectators.add(user);
        players.remove(user);
    }

    @Override
    public void removeSpectator(UserGameSession user) {
        spectators.remove(user);
    }

    @Override
    public boolean isWaiting() {
        return gameState == GameState.WAITING;
    }

    @Override
    public boolean isStarting() {
        return gameState == GameState.STARTING;
    }

    @Override
    public boolean hasStarted() {
        return gameState == GameState.STARTED;
    }

    @Override
    public boolean hasEnded() {
        return gameState == GameState.ENDED;
    }
}
