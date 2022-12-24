package com.ultimismc.gamescaler;

/**
 * @author DirectPlan
 */
public interface ServerPlugin {

    String getName();

    int getOnlinePlayers();

    int getMaximumPlayers();

    boolean isWhitelisted();

    void broadcastMessage(String message);

    void log(String message);

    void shutdown(String reason);
}
