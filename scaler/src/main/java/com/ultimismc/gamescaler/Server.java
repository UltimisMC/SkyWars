package com.ultimismc.gamescaler;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Server implements Serializable {

    private final String displayName;
    private final String id;
    private ServerState state;

    private int onlinePlayers;
    private int maximumPlayers;
    private boolean whitelisted;
    private boolean lobby;

    public Server(String id, String displayName, ServerState state, int onlinePlayers, int maximumPlayers, boolean whitelisted, boolean lobby) {
        this.id = id;
        this.displayName = displayName;
        this.state = state;
        this.onlinePlayers = onlinePlayers;
        this.maximumPlayers = maximumPlayers;
        this.whitelisted = whitelisted;
        this.lobby = lobby;
    }

    public Server(ServerPlugin plugin, String displayName, String id, boolean lobby) {
        this.displayName = displayName;
        this.id = id;
        this.lobby = lobby;
        this.state = ServerState.IDLING;

        this.onlinePlayers = plugin.getOnlinePlayers();
        this.maximumPlayers = plugin.getMaximumPlayers();
        this.whitelisted = plugin.isWhitelisted();
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
