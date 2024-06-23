package com.ultimismc.serversync.communication.listener;

import com.ultimismc.serversync.Server;
import com.ultimismc.serversync.ServerManager;
import com.ultimismc.serversync.communication.ServerChannelConstants;
import com.ultimismc.serversync.serializer.Serializer;

/**
 * @author DirectPlan
 */
public class UpdateChannelMessage<T extends Server> extends ServerMessage<T> {

    public UpdateChannelMessage(ServerManager<?, T> serverManager) {
        super(serverManager, ServerChannelConstants.SERVER_UPDATE, serverManager.getGameClazz());
    }

    @Override
    public void onMessageReceived(T server, Serializer serializer) {
        log("Received server update from " + server.getId() + ".");
        serverManager.updateServer(server);
    }
}
