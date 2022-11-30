package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class KillMessageBundle {

    @Getter private final Map<MessageType, MessageResolver> messageResolvers = new LinkedHashMap<>();

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
