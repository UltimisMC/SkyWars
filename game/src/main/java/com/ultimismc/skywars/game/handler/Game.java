package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.events.GameStartedEvent;
import com.ultimismc.skywars.core.events.GameStateChangedEvent;
import com.ultimismc.skywars.core.game.GameState;
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
public abstract class Game {

    protected final SkyWarsPlugin plugin;
    protected final GameHandler gameHandler;
    private final GameChestRegistry chestRegistry;

    private final int minimumPlayers = 8;
    private GameState gameState = GameState.WAITING;

    private final LinkedList<UserGameSession> playersLeft = new LinkedList<>();
    private final LinkedList<UserGameSession> spectators = new LinkedList<>();

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        plugin.callEvent(new GameStateChangedEvent(gameState));

        if(gameState == GameState.STARTED) {
            for(UserGameSession userGameSession : gameHandler.getUserSessions()) {
                plugin.callEvent(new GameStartedEvent(userGameSession.getUser()));
            }
        }
    }

    public void startGame() {}

    public void endGame() {}

    public void prepareUser(UserGameSession user) {
        if(!isJoinable()) return;
        playersLeft.add(user);
    }

    public void quitUser(UserGameSession user) {
        playersLeft.remove(user);
        removeSpectator(user);
    }

    public void addSpectator(UserGameSession user) {
        spectators.add(user);
    }

    public void terminatePlayer(UserGameSession userGameSession) {
        playersLeft.remove(userGameSession);
    }

    public void removeSpectator(UserGameSession user) {
        spectators.remove(user);
    }

    public boolean isWaiting() {
        return gameState == GameState.WAITING;
    }

    public boolean isStarting() {
        return gameState == GameState.STARTING;
    }

    public boolean hasStarted() {
        return gameState == GameState.STARTED; // Could break something?
    }

    public boolean isJoinable() {
        return (gameState == GameState.WAITING || gameState == GameState.STARTING);
    }

    public boolean hasEnded() {
        return gameState == GameState.ENDED;
    }

    public boolean isRestarting() {
        return gameState == GameState.RESTARTING;
    }
}
