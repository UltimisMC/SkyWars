package com.ultimismc.gamescaler.communication.listener;

import com.ultimismc.gamescaler.Server;
import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.communication.ServerChannelConstants;
import com.ultimismc.gamescaler.communication.ServerFetchPackage;
import com.ultimismc.gamescaler.serializer.Serializer;

import java.util.List;

/**
 * @author DirectPlan
 */
public class ServerFetchChannelListener extends ServerListener<ServerFetchPackage> {

    public ServerFetchChannelListener(ServerManager<?> serverManager) {
        super(serverManager, ServerChannelConstants.FETCH_SERVER, ServerFetchPackage.class);
    }

    @Override
    public void onMessageReceived(ServerFetchPackage serverPackage, Serializer serializer) {

        Server destination = serverPackage.getDestination();
        List<Server> availableServers = serverPackage.getAvailableServers();

    }
}
