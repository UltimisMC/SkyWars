package com.ultimismc.skywars.core.game.features.cosmetics.victorydances;

import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public class VictoryDanceRunnable implements Runnable {

    private final User user;
    private final VictoryDance dance;

    private int ticks = 0;
    private final VictoryDanceExecutor danceExecutor;

    public VictoryDanceRunnable(User user, VictoryDance dance) {
        this.user = user;
        this.dance = dance;
        this.danceExecutor = dance.executor();
    }

    @Override
    public void run() {
        ticks += dance.getIntervalTicks();
        danceExecutor.executeDance(user, ticks);
    }
}
