package com.ultimismc.skywars.core.server;

import com.ultimismc.serversync.Server;
import com.ultimismc.serversync.ServerPlugin;
import com.ultimismc.skywars.core.game.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Getter
public class SkyWarsServer extends Server {


    private final GameType gameType;
    private final TeamType teamType;
    private final Map map;
    @Setter private GameState state;

    public SkyWarsServer(ServerPlugin plugin, GameConfig gameConfig) {
        super(plugin, gameConfig.getServerId(), "SkyWars " + gameConfig.getName(), gameConfig.isLobby());

        gameType = gameConfig.getGameType();
        teamType = gameConfig.getTeamType();
        map = gameConfig.getMap();
        state = GameState.WAITING;
    }

    public boolean isWaiting() {
        return state == GameState.WAITING;
    }

    public boolean isStarting() {
        return state == GameState.STARTING;
    }

    public boolean hasStarted() {
        return !isJoinable(); // Could break something?
    }

    public boolean isJoinable() {
        return (state == GameState.WAITING || state == GameState.STARTING);
    }

    public boolean isRestarting() {
        return state == GameState.RESTARTING;
    }

    public String getMapName() {
        return map.getName();
    }

    @Override
    public void updateVariables(Server other) {
        SkyWarsServer skyWarsServer = (SkyWarsServer) other;
        state = skyWarsServer.getState();
    }
}
