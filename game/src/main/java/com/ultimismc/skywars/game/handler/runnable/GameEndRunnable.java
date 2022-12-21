package com.ultimismc.skywars.game.handler.runnable;

import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.handler.end.GameEndPhase;
import com.ultimismc.skywars.game.handler.end.GameEndRebootPhase;
import com.ultimismc.skywars.game.handler.end.GameEndRewardsPhase;
import com.ultimismc.skywars.game.handler.end.GameEndWinPhase;
import com.ultimismc.skywars.game.handler.team.GameTeam;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author DirectPlan
 */
public class GameEndRunnable implements Runnable {

    private final GameTeam winnerTeam;
    private final Collection<GameTeam> teams;

    private long currentTime = System.currentTimeMillis();
    private final LinkedList<GameEndPhase> endPhases = new LinkedList<>();

    public GameEndRunnable(GameHandler gameHandler, GameTeam winnerTeam, Collection<GameTeam> teams) {
        this.winnerTeam = winnerTeam;
        this.teams = teams;

        endPhases.addLast(new GameEndWinPhase(gameHandler));
        endPhases.addLast(new GameEndRewardsPhase(gameHandler));
        endPhases.addLast(new GameEndRebootPhase(gameHandler));
    }

    @Override
    public void run() {
        GameEndPhase phase = endPhases.getFirst();
        if(phase == null) return;
        int executeIn = phase.getExecuteIn();
        boolean timePassed = hasTimePassed(executeIn);
        if(!timePassed) return;
        phase.executePhase(winnerTeam, teams);
        endPhases.removeFirst();
        currentTime = System.currentTimeMillis();
    }

    private boolean hasTimePassed(int seconds) {
        long millis = (seconds * 1000L);
        return (System.currentTimeMillis() - currentTime) >= millis;
    }
}
