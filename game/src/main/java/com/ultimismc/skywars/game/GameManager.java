package com.ultimismc.skywars.game;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class GameManager {

    private final SkyWarsPlugin plugin;

    private final GameServerInitializer serverInitializer;

    public GameManager(SkyWarsPlugin plugin) {
        this.plugin = plugin;

        serverInitializer = new GameServerInitializer();
    }

    public void initialize() {
        serverInitializer.initializeServer();
    }

    public void shutdown() {
        serverInitializer.finalizeServer();
    }

    public void handleJoin(User user) {

    }

    public void handleQuit(User user) {

    }

    public GameServer getGameServer() {
        return serverInitializer.getGameServer();
    }
}
