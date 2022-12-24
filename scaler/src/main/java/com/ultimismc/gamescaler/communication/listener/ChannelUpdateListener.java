package com.ultimismc.gamescaler.communication.listener;

import com.ultimismc.gamescaler.GameServer;
import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.serializer.Serializer;

/**
 * @author DirectPlan
 */
public class ChannelUpdateListener extends ServerListener<GameServer> {

    public ChannelUpdateListener(ServerManager serverManager) {
        super(serverManager, GameServer.class);
    }

    @Override
    public void onMessageReceived(GameServer server, Serializer serializer) {

    }
}
