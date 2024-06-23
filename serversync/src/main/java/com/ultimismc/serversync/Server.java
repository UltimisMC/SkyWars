package com.ultimismc.serversync;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Server implements Serializable {

    private final String id;
    private final String displayName;

    private int onlinePlayers;
    private int maximumPlayers;
    private boolean whitelisted;
    private boolean lobby;

    private long lastHeartbeat;

    public Server(String id, String displayName, int onlinePlayers, int maximumPlayers, boolean whitelisted, boolean lobby) {
        this.id = id;
        this.displayName = displayName;
        this.onlinePlayers = onlinePlayers;
        this.maximumPlayers = maximumPlayers;
        this.whitelisted = whitelisted;
        this.lobby = lobby;
    }

    public Server(ServerPlugin plugin, String id, String displayName, boolean lobby) {
        this.id = id;
        this.displayName = displayName;
        this.lobby = lobby;

        this.onlinePlayers = plugin.getOnlinePlayers();
        this.maximumPlayers = plugin.getMaximumPlayers();
        this.whitelisted = plugin.isWhitelisted();
    }

    public boolean isFull() {
        return onlinePlayers >= maximumPlayers;
    }

    public void updateVariables(Server other) {}
}
