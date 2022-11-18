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
        SkyWarsEvent nextEvent = skyWarsEventHandler.getNextEvent();
        if(nextEvent == null) return;

        for(SkyWarsEventUpdater updater : skyWarsEventHandler.getUpdaters()) {
            updater.update(skyWarsEventHandler);
        }

        long timeLeft = skyWarsEventHandler.getUntilNextEvent();
        if(timeLeft > 0) return;
        skyWarsEventHandler.executeNextEvent();
    }
}
