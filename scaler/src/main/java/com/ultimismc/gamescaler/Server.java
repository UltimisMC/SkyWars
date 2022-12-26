package com.ultimismc.gamescaler;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class Server implements Serializable {

    private final String displayName;
    private final String id;

    private int onlinePlayers;
    private int maximumPlayers;
    private boolean whitelisted;
    private boolean lobby;

    public Server(String id, String displayName, int onlinePlayers, int maximumPlayers, boolean whitelisted, boolean lobby) {
        this.id = id;
        this.displayName = displayName;
        this.onlinePlayers = onlinePlayers;
        this.maximumPlayers = maximumPlayers;
        this.whitelisted = whitelisted;
        this.lobby = lobby;
    }

    public Server(ServerPlugin plugin, String displayName, String id, boolean lobby) {
        this.displayName = displayName;
        this.id = id;
        this.lobby = lobby;

        this.onlinePlayers = plugin.getOnlinePlayers();
        this.maximumPlayers = plugin.getMaximumPlayers();
        this.whitelisted = plugin.isWhitelisted();
    }

    public abstract void updateVariables(Server other);
}
