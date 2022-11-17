package com.ultimismc.skywars.game.events;

import com.ultimismc.skywars.game.handler.GameHandler;

/**
 * @author DirectPlan
 */
public class RefillSkyWarsEvent extends AbstractSkyWarsEvent {

    public RefillSkyWarsEvent(long scheduledIn) {
        super("Refill", scheduledIn);
    }

    @Override
    public void executeEvent(GameHandler gameHandler) {

    }
}
