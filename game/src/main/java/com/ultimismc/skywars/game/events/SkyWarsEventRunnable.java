package com.ultimismc.skywars.game.events;

import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class SkyWarsEventRunnable implements Runnable {

    private final SkyWarsEventHandler skyWarsEventHandler;

    @Override
    public void run() {
        if(skyWarsEventHandler.getNextEvent() == null) return;

        long timeLeft = skyWarsEventHandler.getUntilNextEvent();
        if(timeLeft > 0) return;
        skyWarsEventHandler.executeNextEvent();
    }
}
