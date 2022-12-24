package com.ultimismc.gamescaler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class GameServer {

    private final ServerPlugin plugin;
    private final String displayName;
    private final String id;

    private ServerState state;
    private boolean lobby;

    public GameServer(ServerPlugin plugin, String displayName, String id, boolean lobby) {
        this(plugin, displayName, id);
        this.lobby = lobby;
    }


    public int getOnlinePlayers() {
        return plugin.getOnlinePlayers();
    }

    public int getMaximumPlayers() {
        return plugin.getMaximumPlayers();
    }

    public boolean isWhitelisted() {
        return plugin.isWhitelisted();
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
