package com.ultimismc.gamescaler.communication.listener;

import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.ServerPlugin;
import com.ultimismc.gamescaler.communication.ServerChannel;
import com.ultimismc.gamescaler.serializer.Serializer;
import redis.clients.jedis.JedisPubSub;

import java.io.Serializable;

/**
 * @author DirectPlan
 */
public abstract class ServerListener<T> extends JedisPubSub {

    protected final ServerPlugin plugin;

    protected final ServerManager<? extends Serializable> serverManager;
    private final ServerChannel channel;
    private final Class<T> castClass;

    public ServerListener(ServerManager<?> serverManager, ServerChannel channel, Class<T> castClass) {
        this.serverManager = serverManager;
        this.channel = channel;
        this.castClass = castClass;
        plugin = serverManager.getPlugin();
    }

    @Override
    public void onMessage(String unused, String json) {
        Serializer serializer = serverManager.getSerializer();
        T message = serializer.deserialize(json, castClass);
        log("Received a json message: " + json);

        onMessageReceived(message, serializer);
    }

    public abstract void onMessageReceived(T message, Serializer serializer);

    public void log(String message) {
        String channelName = channel.getName();

        plugin.log("[LISTENER: " + channelName + "] " + message);
    }
}
