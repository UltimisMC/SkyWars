package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class GameStateChangedEvent extends SkyWarsEvent {

    private final GameState gameState;
}
