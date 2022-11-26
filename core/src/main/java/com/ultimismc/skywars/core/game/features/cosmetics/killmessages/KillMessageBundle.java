package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import com.ultimismc.skywars.core.user.User;

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

    public String resolveScreenMessage(User user, User killer) {
        MessageResolver messageResolver = getResolver(MessageType.SCREEN);
        return messageResolver.resolveMessage(user, killer);
    }
}
