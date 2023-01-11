package com.ultimismc.skywars.game.events;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.game.handler.GameHandler;

/**
 * @author DirectPlan
 */
public class GameEndedSkyWarsEvent extends AbstractSkyWarsEvent {

    public GameEndedSkyWarsEvent(long scheduledIn) {
        super("Game End", scheduledIn);
    }

    @Override
    public void prepare(GameHandler gameHandler) {

    }

    @Override
    public void executeEvent(GameHandler gameHandler) {
        SkyWarsPlugin plugin = gameHandler.getPlugin();
        plugin.shutdownServer("Game Ended");
    }
}
