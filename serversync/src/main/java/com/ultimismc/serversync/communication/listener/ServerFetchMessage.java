package com.ultimismc.serversync.communication.listener;

import com.ultimismc.serversync.ClientServerManager;
import com.ultimismc.serversync.communication.ServerChannelConstants;
import com.ultimismc.serversync.serializer.Serializer;

/**
 * @author DirectPlan
 */
public class ServerFetchMessage extends ServerMessage<String> {

    public ServerFetchMessage(ClientServerManager<?> serverManager) {
        super(serverManager, ServerChannelConstants.FETCH_SERVER, String.class);
    }

    @Override
    public void onMessageReceived(String requestedServer, Serializer serializer) {
        ClientServerManager<?> clientServerManager = (ClientServerManager<?>) serverManager;
        String serverId = clientServerManager.getServerId();
        if(!(serverId.equals("ALL") || serverId.equals(requestedServer))) return;
        log("Received a fetch request. Sending server update...");
        clientServerManager.sendServerUpdate();
    }
}
