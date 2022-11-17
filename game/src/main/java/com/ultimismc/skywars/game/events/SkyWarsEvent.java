package com.ultimismc.skywars.game.events;

import com.ultimismc.skywars.game.handler.GameHandler;

/**
 * @author DirectPlan
 */
public interface SkyWarsEvent {

    String getName();

    long getScheduledIn();

    void executeEvent(GameHandler gameHandler);
}
