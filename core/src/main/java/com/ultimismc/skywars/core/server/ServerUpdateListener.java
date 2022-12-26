package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.Server;
import com.ultimismc.skywars.core.events.GameStateChangedEvent;
import com.ultimismc.skywars.core.game.GameState;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ServerUpdateListener implements Listener {

    private final SkyWarsServerManager serverManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        serverManager.sendServerUpdate();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        serverManager.sendServerUpdate();
    }

    @EventHandler
    public void onGameStateChange(GameStateChangedEvent event) {
        GameState state = event.getGameState();

        SkyWarsServer server = serverManager.getServer();
        server.setState(state);
        serverManager.sendServerUpdate();
    }
}
