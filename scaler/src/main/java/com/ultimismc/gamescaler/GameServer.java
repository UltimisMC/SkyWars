package com.ultimismc.gamescaler;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GameServer implements Serializable {

    private final ServerPlugin plugin;
    private final String displayName;
    private final String id;

    private ServerState state;

    private final ServerInfo serverInfo;

    public GameServer(ServerPlugin plugin, String displayName, String id, boolean lobby) {
        this.plugin = plugin;
        this.displayName = displayName;
        this.id = id;

        serverInfo = new ServerInfo(plugin, lobby);
    }

    public boolean isLobby() {
        return serverInfo.isLobby();
    }

    public int getOnlinePlayers() {
        return serverInfo.getOnlinePlayers();
    }

    public int getMaximumPlayers() {
        return serverInfo.getMaximumPlayers();
    }

    public boolean isWhitelisted() {
        return serverInfo.isWhitelisted();
    }

    public boolean isIdling() { // Or Waiting
        return state == ServerState.IDLING;
    }

    public boolean isStarting() {
        return state == ServerState.STARTING;
    }

    public boolean hasStarted() {
        return state == ServerState.STARTED;
    }
}
