package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.Server;
import com.ultimismc.gamescaler.ServerPlugin;

import com.ultimismc.skywars.core.game.GameConfig;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class SkyWarsServer extends Server {

    public SkyWarsServer(ServerPlugin plugin, GameConfig gameConfig) {
        super(plugin, gameConfig.getServerId(), "SkyWars " + gameConfig.getName(), gameConfig.isLobby());
    }
}
