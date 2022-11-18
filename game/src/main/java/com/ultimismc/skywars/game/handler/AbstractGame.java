package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.chest.GameChestRegistry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
@Setter
public abstract class AbstractGame implements Game {

    private final GameHandler gameHandler;
    private final GameChestRegistry chestRegistry;

    private final int minimumPlayers = 8;
    private GameState gameState = GameState.WAITING;
    private final List<User> players = new ArrayList<>();
    private final List<User> spectators = new ArrayList<>();



    @Override
    public void startGame() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void prepareUser(User user) {
        players.add(user);
    }

    @Override
    public void quitUser(User user) {
        players.remove(user);
        removeSpectator(user);
    }

    @Override
    public void addSpectator(User user) {
        spectators.add(user);
    }

    @Override
    public void removeSpectator(User user) {
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
}
