package com.ultimismc.skywars.game.events;

import com.ultimismc.skywars.game.handler.GameHandler;

/**
 * @author DirectPlan
 */
public class GameEndedSkyWarsEvent extends AbstractSkyWarsEvent {

    public GameEndedSkyWarsEvent(long scheduledIn) {
        super("Game Ended!", scheduledIn);
    }

    @Override
    public void executeEvent(GameHandler gameHandler) {

    }
}
