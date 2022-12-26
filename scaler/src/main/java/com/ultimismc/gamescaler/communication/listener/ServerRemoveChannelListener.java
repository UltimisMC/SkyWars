package com.ultimismc.gamescaler.communication.listener;

import com.ultimismc.gamescaler.Server;
import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.communication.ServerChannelConstants;
import com.ultimismc.gamescaler.serializer.Serializer;

/**
 * @author DirectPlan
 */
public class ServerRemoveChannelListener extends ServerListener<Server> {

    public ServerRemoveChannelListener(ServerManager<?> serverManager) {
        super(serverManager, ServerChannelConstants.SERVER_REMOVE, Server.class);
    }

    @Override
    public void onMessageReceived(Server server, Serializer serializer) {
        serverManager.removeServer(server);
        log("Server '" + server.getId() + "' has been removed.");
    }
}
