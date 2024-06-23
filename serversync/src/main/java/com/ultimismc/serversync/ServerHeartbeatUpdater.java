package com.ultimismc.serversync;

import com.ultimismc.serversync.communication.ServerChannelConstants;
import lombok.RequiredArgsConstructor;

import java.util.TimerTask;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ServerHeartbeatUpdater<S extends Server> extends TimerTask {

    private final S server;
    private final ServerManager<?, S> serverManager;
    private final long timeout;

    @Override
    public void run() {
        String serverId = server.getId();
        long current = System.currentTimeMillis();
        for(Server server : serverManager.getServers()) {
            long lastHeartbeat = server.getLastHeartbeat();
            if((current - lastHeartbeat) < timeout) continue;
            // Server timed out
            // Removing server...
            serverManager.removeServer(server);
            serverManager.log("Server '" + serverId + "' has timed out and removed!");

        }
        // Sending server heartbeat
        serverManager.sendRequest(ServerChannelConstants.HEART_BEAT, serverId);
    }
}
