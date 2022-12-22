package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;

/**
 * @author DirectPlan
 */
public class GameStartedEvent extends AbstractEvent {

    public GameStartedEvent(GameConfig gameConfig) {
        super(gameConfig);
    }
}
