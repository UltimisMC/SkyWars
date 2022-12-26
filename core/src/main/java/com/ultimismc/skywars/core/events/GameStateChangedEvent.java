package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameState;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class GameStateChangedEvent extends AbstractEvent {

    private final GameState gameState;

    public GameStateChangedEvent(GameConfig gameConfig, GameState gameState) {
        super(gameConfig);

        this.gameState = gameState;
    }
}
