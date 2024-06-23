package com.ultimismc.serversync.communication.listener;

import com.ultimismc.serversync.ServerManager;
import com.ultimismc.serversync.communication.ServerChannelConstants;
import com.ultimismc.serversync.serializer.Serializer;

/**
 * @author DirectPlan
 */
public class TestChannelMessage extends ServerMessage<Object> {

    public TestChannelMessage(ServerManager<?, ?> serverManager) {
        super(serverManager, ServerChannelConstants.TEST, Object.class);
    }

    @Override
    public void onMessage(String unused, String message) {
        System.out.println("Received a test message: " + message);
    }

    @Override
    public void onMessageReceived(Object message, Serializer serializer) {}
}
