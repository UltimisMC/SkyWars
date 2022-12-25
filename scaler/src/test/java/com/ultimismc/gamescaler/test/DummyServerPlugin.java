package com.ultimismc.gamescaler.test;

import com.ultimismc.gamescaler.ServerPlugin;

/**
 * @author DirectPlan
 */
public class DummyServerPlugin implements ServerPlugin {
    @Override
    public String getName() {
        return "Dummy";
    }

    @Override
    public int getOnlinePlayers() {
        return 5;
    }

    @Override
    public int getMaximumPlayers() {
        return 100;
    }

    @Override
    public boolean isWhitelisted() {
        return false;
    }

    @Override
    public void broadcastMessage(String message) {
        System.out.println("[BROADCAST]: " + message);
    }

    @Override
    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }

    @Override
    public void shutdown(String reason) {
        System.out.println("SHUTTING DOWN FOR " + reason);
        System.exit(1);
    }
}
