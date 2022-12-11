package com.ultimismc.skywars.game.handler.runnable;

import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.handler.end.GameEndPhase;
import com.ultimismc.skywars.game.handler.end.GameEndRebootPhase;
import com.ultimismc.skywars.game.handler.end.GameEndRewardsPhase;
import com.ultimismc.skywars.game.handler.end.GameEndStatsPhase;
import com.ultimismc.skywars.game.user.UserGameSession;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author DirectPlan
 */
public class GameEndRunnable implements Runnable {

    private final UserGameSession winner;
    private final Collection<UserGameSession> participators;

    private long currentTime = System.currentTimeMillis();
    private final LinkedList<GameEndPhase> endPhases = new LinkedList<>();

    public GameEndRunnable(GameHandler gameHandler, UserGameSession winner, Collection<UserGameSession> participators) {
        this.winner = winner;
        this.participators = participators;

        endPhases.addLast(new GameEndStatsPhase(gameHandler));
        endPhases.addLast(new GameEndRewardsPhase(gameHandler));
        endPhases.addLast(new GameEndRebootPhase(gameHandler));
    }

    @Override
    public void run() {
        GameEndPhase phase = endPhases.poll();
        if(phase == null) return;
        int executeIn = phase.getExecuteIn();
        boolean timePassed = hasTimePassed(executeIn);
        System.out.println("Has time passed: " + timePassed);
        if(!timePassed) return;

        System.out.println("Executing phase: " + endPhases.indexOf(phase));
        phase.executePhase(winner, participators);
        currentTime = System.currentTimeMillis();
    }

    private boolean hasTimePassed(int seconds) {
        long millis = (seconds * 1000L);
        return (System.currentTimeMillis() - currentTime) >= millis;
    }
}
