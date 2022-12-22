package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;

/**
 * @author DirectPlan
 */
public class GameStartingEvent extends AbstractEvent {

    public GameStartingEvent(GameConfig gameConfig) {
        super(gameConfig);
    }
}
