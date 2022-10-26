package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameServer;

/**
 * @author DirectPlan
 */
public class GameStartingEvent extends AbstractEvent {

    public GameStartingEvent(GameServer gameServer) {
        super(gameServer);
    }
}
