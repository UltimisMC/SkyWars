package com.ultimismc.serversync.communication.listener;

import com.ultimismc.serversync.Server;
import com.ultimismc.serversync.ServerManager;
import com.ultimismc.serversync.communication.ServerChannelConstants;
import com.ultimismc.serversync.serializer.Serializer;
import lombok.var;

/**
 * @author DirectPlan
 */
public class ServerHeartbeatMessage<T extends Server> extends ServerMessage<String> {

    public ServerHeartbeatMessage(ServerManager<?, T> serverManager) {
        super(serverManager, ServerChannelConstants.HEART_BEAT, String.class);
    }

    @Override
    public void onMessageReceived(String serverId, Serializer serializer) {
        var server = serverManager.getServer(serverId);
        if(server == null) {
            // Correcting connection/communication failure.
            log("Unknown heart beat received from '" + serverId + "'. Sending fetch request to " + serverId + "...");
            serverManager.fetchServer(serverId);
            return;
        }
        server.setLastHeartbeat(System.currentTimeMillis());
    }
}
