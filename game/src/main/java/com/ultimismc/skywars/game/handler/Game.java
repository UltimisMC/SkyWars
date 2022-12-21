package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.game.chest.GameChestRegistry;
import com.ultimismc.skywars.game.handler.team.GameTeam;
import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Optional;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
@Setter
public abstract class Game {

    protected final GameHandler gameHandler;
    private final GameChestRegistry chestRegistry;

    private final int minimumPlayers = 8;
    private GameState gameState = GameState.WAITING;
    private final LinkedList<GameTeam> gameTeams = new LinkedList<>();

    private final LinkedList<UserGameSession> playersLeft = new LinkedList<>();
    private final LinkedList<UserGameSession> spectators = new LinkedList<>();


    public void startGame() {}

    public void endGame() {}

    public void addGameTeam(GameTeam gameTeam) {
        gameTeams.add(gameTeam);
    }

    public void removeGameTeam(GameTeam gameTeam) {
        gameTeams.remove(gameTeam);
    }

    public void prepareUser(UserGameSession user) {
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

    }

    public GameTeam getLastTeamAlive() {
        Optional<GameTeam> lastTeamOptional = gameTeams.stream().filter(GameTeam::isAlive).findFirst();
        if(!lastTeamOptional.isPresent()) {
            throw new RuntimeException("Could not find any alive team. Game Teams is empty? Size: " + gameTeams.size());
        }
        return lastTeamOptional.get();
    }

    public int getTeamsLeft() {
        int teamsLeft = 0;
        for(GameTeam gameTeam : gameTeams) {
            if(!gameTeam.isAlive()) continue;
            teamsLeft++;
        }
        return teamsLeft;
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
        return gameState != GameState.WAITING && gameState != GameState.STARTING;
    }

    public boolean hasEnded() {
        return gameState == GameState.ENDED;
    }

    public boolean isRestarting() {
        return gameState == GameState.RESTARTING;
    }
}
