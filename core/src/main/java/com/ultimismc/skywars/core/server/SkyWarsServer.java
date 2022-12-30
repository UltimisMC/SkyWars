package com.ultimismc.skywars.core.server;

import com.ultimismc.serversync.Server;
import com.ultimismc.serversync.ServerPlugin;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameState;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Getter
public class SkyWarsServer extends Server {


    private final GameType gameType;
    private final TeamType teamType;
    @Setter private GameState state;

    public SkyWarsServer(ServerPlugin plugin, GameConfig gameConfig) {
        super(plugin, gameConfig.getServerId(), "SkyWars " + gameConfig.getName(), gameConfig.isLobby());

        gameType = gameConfig.getGameType();
        teamType = gameConfig.getTeamType();
        state = GameState.WAITING;
    }

    public boolean isWaiting() {
        return state == GameState.WAITING;
    }

    public boolean isStarting() {
        return state == GameState.STARTING;
    }

    public boolean hasStarted() {
        return state != GameState.WAITING && state != GameState.STARTING;
    }

    @Override
    public void updateVariables(Server other) {
        SkyWarsServer skyWarsServer = (SkyWarsServer) other;
        state = skyWarsServer.getState();
    }
}
