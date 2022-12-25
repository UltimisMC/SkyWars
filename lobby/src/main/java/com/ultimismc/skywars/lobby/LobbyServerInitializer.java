package com.ultimismc.skywars.lobby;

import com.ultimismc.skywars.core.ServerInitializer;
import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.Map;

/**
 * @author DirectPlan
 */
public class LobbyServerInitializer implements ServerInitializer {

    @Override
    public GameConfig loadGameConfig(SkyWarsPlugin plugin) {
        return new GameConfig("LOBBY", null, null, null, new Map("Main Lobby"), true);
    }

    @Override
    public void saveGameConfig(SkyWarsPlugin plugin) {}
}
