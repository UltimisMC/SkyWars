package com.ultimismc.skywars.game.events;

import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@Getter
@RequiredArgsConstructor
public abstract class AbstractSkyWarsEvent implements SkyWarsEvent {

    private final String name;
    private final long scheduledIn;

    public abstract void executeEvent(GameHandler gameHandler);
}
