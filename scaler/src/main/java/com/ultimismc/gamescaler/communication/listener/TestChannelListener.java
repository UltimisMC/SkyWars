package com.ultimismc.gamescaler.communication.listener;

import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.communication.ServerChannelConstants;
import com.ultimismc.gamescaler.serializer.Serializer;

/**
 * @author DirectPlan
 */
public class TestChannelListener extends ServerListener<Object> {

    public TestChannelListener(ServerManager<?> serverManager) {
        super(serverManager, ServerChannelConstants.TEST, Object.class);
    }

    @Override
    public void onMessage(String unused, String message) {
        System.out.println("Received a test message: " + message);
    }

    @Override
    public void onMessageReceived(Object message, Serializer serializer) {

    }
}
