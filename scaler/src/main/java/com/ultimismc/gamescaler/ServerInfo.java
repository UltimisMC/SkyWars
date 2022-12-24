package com.ultimismc.gamescaler;

import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class ServerInfo {

    private final ServerPlugin plugin;

    private final boolean lobby;

    public ServerInfo(ServerPlugin plugin, boolean lobby) {
        this.plugin = plugin;
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
}
