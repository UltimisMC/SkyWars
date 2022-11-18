package com.ultimismc.skywars.game.events;

import com.ultimismc.skywars.game.handler.GameHandler;

/**
 * @author DirectPlan
 */
public class TestSkyWarsEvent extends AbstractSkyWarsEvent {

    public TestSkyWarsEvent(String name) {
        super(name, 15 * 1000L);
    }

    @Override
    public void executeEvent(GameHandler gameHandler) {
        gameHandler.broadcastMessage("&aTest event &e" + getName() + "&a has executed!");
    }
}
