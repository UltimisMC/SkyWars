package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameServer;

/**
 * @author DirectPlan
 */
public class GameStartedEvent extends AbstractEvent {

    public GameStartedEvent(GameServer gameServer) {
        super(gameServer);
    }
}
