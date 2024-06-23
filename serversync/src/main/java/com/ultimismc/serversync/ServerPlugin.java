package com.ultimismc.serversync;

/**
 * @author DirectPlan
 */
public interface ServerPlugin extends ServerSoftware {

    int getOnlinePlayers();

    int getMaximumPlayers();

    boolean isWhitelisted();

    void broadcastMessage(String message);
}
