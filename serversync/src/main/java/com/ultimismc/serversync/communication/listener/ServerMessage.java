package com.ultimismc.serversync.communication.listener;

import com.ultimismc.serversync.ServerManager;
import com.ultimismc.serversync.ServerSoftware;
import com.ultimismc.serversync.communication.ServerChannel;
import com.ultimismc.serversync.serializer.Serializer;
import lombok.Getter;
import redis.clients.jedis.JedisPubSub;

/**
 * @author DirectPlan
 */
public abstract class ServerMessage<T> extends JedisPubSub {

    protected final ServerSoftware software;

    protected final ServerManager<?, ?> serverManager;
    @Getter private final ServerChannel channel;
    protected final Class<T> castClass;

    public ServerMessage(ServerManager<?, ?> serverManager, ServerChannel channel, Class<T> castClass) {
        this.serverManager = serverManager;
        this.channel = channel;
        this.castClass = castClass;
        software = serverManager.getSoftware();
    }

    @Override
    public void onMessage(String unused, String json) {
        Serializer serializer = serverManager.getSerializer();
        T message = serializer.deserialize(json, castClass);

        onMessageReceived(message, serializer);
    }

    public abstract void onMessageReceived(T message, Serializer serializer);

    public void log(String message) {
        String channelName = channel.getName();

        serverManager.log("[LISTENER: " + channelName + "] " + message);
    }
}
