package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.Server;
import com.ultimismc.gamescaler.ServerPlugin;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameState;
import lombok.Getter;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Getter
public class SkyWarsServer extends Server {

    @Setter private GameState state;

    public SkyWarsServer(ServerPlugin plugin, GameConfig gameConfig) {
        super(plugin, gameConfig.getServerId(), "SkyWars " + gameConfig.getName(), gameConfig.isLobby());

        state = GameState.WAITING;
    }

    public boolean isWaiting() {
        return state == GameState.WAITING;
    }

    public boolean isStarting() {
        return state == GameState.STARTING;
    }

    public boolean hasStarted() {
        return state == GameState.STARTED;
    }

    @Override
    public void updateVariables(Server other) {
        SkyWarsServer skyWarsServer = (SkyWarsServer) other;
        state = skyWarsServer.getState();
    }
}
