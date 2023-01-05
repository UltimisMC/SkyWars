package com.ultimismc.skywars.core.server;

import com.ultimismc.serversync.Server;
import com.ultimismc.serversync.communication.ServerChannelConstants;
import com.ultimismc.skywars.core.events.GameStateChangedEvent;
import com.ultimismc.skywars.core.game.GameState;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
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
        Server server = serverManager.getServer();
        server.setOnlinePlayers(Bukkit.getOnlinePlayers().size());

        serverManager.sendServerMessage(ServerChannelConstants.SERVER_PLAYERS_UPDATED);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Server server = serverManager.getServer();
        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        server.setOnlinePlayers(onlinePlayers - 1);
        serverManager.sendServerMessage(ServerChannelConstants.SERVER_PLAYERS_UPDATED);
    }

    @EventHandler
    public void onGameStateChange(GameStateChangedEvent event) {
        GameState state = event.getGameState();

        SkyWarsServer server = serverManager.getServer();
        server.setState(state);
        serverManager.sendServerMessage(ServerChannelConstants.SERVER_STATE_UPDATED);
    }
}
