package com.ultimismc.serversync.communication.listener;

import com.ultimismc.serversync.Server;
import com.ultimismc.serversync.ServerManager;
import com.ultimismc.serversync.communication.ServerChannelConstants;
import com.ultimismc.serversync.serializer.Serializer;

/**
 * @author DirectPlan
 */
public class ServerShutdownMessage extends ServerMessage<Server> {

    public ServerShutdownMessage(ServerManager<?, ?> serverManager) {
        super(serverManager, ServerChannelConstants.SERVER_SHUTDOWN, Server.class);
    }

    @Override
    public void onMessageReceived(Server server, Serializer serializer) {
        serverManager.removeServer(server);
        log("Server '" + server.getId() + "' has been removed.");
    }
}
