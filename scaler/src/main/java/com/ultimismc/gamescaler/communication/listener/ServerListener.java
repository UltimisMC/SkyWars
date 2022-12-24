package com.ultimismc.gamescaler.communication.listener;

import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.serializer.Serializer;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPubSub;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public abstract class ServerListener<T> extends JedisPubSub {

    protected final ServerManager serverManager;
    private final Class<T> castClass;

    @Override
    public void onMessage(String unused, String json) {
        Serializer serializer = serverManager.getSerializer();
        T message = serializer.deserialize(json, castClass);
        onMessageReceived(message, serializer);
    }

    public abstract void onMessageReceived(T message, Serializer serializer);
}
