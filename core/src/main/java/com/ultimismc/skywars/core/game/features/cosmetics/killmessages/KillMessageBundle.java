package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class KillMessageBundle {

    private final Map<MessageType, MessageResolver> messageResolvers = new HashMap<>();

    public void addResolver(MessageType messageType, MessageResolver resolver) {
        messageResolvers.put(messageType, resolver);
    }

    public MessageResolver getResolver(MessageType messageType) {
        return messageResolvers.get(messageType);
    }
}
