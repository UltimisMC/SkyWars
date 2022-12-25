package com.ultimismc.gamescaler;

import com.google.gson.JsonElement;
import com.ultimismc.gamescaler.communication.ServerChannel;
import com.ultimismc.gamescaler.communication.ConnectionData;
import com.ultimismc.gamescaler.communication.JedisConnection;
import com.ultimismc.gamescaler.communication.ServerChannelConstants;
import com.ultimismc.gamescaler.communication.listener.TestChannelListener;
import com.ultimismc.gamescaler.communication.listener.UpdateChannelListener;
import com.ultimismc.gamescaler.serializer.GsonSerializer;
import com.ultimismc.gamescaler.serializer.Serializer;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Getter
public abstract class ServerManager<S extends Server> {

    private final Map<String, S> servers = new HashMap<>();

    private final ServerPlugin plugin;

    private final JedisConnection connection;
    private final Serializer serializer;

    private final Class<S> gameClazz;

    public ServerManager(ServerPlugin plugin, Serializer serializer, Class<S> gameClazz) {
        this.plugin = plugin;
        this.serializer = serializer;
        this.gameClazz = gameClazz;
        this.connection = new JedisConnection(plugin);
    }

    public ServerManager(ServerPlugin plugin, Class<S> gameClazz) {
        this(plugin, new GsonSerializer(), gameClazz);
    }


    public void connect(ConnectionData connectionData) {
        // Establish connection.

        plugin.log("Establishing connection with Redis server...");
        if(!connection.establishConnection(connectionData)) {
            plugin.shutdown("Could not connect to redis server.");
            return;
        }
        plugin.log("Connection with Redis server has been established!");
        Server server = wrap(plugin);

        plugin.log("Subscribing to server channels...");
        connection.subscribe(ServerChannelConstants.SERVER_UPDATE, new UpdateChannelListener<>(this));
        connection.subscribe(ServerChannelConstants.TEST, new TestChannelListener(this));

        plugin.log("Broadcasting server data...");

        sendRequest(ServerChannelConstants.SERVER_UPDATE, server);
        // If everything is working correctly. The server should be registered from SERVER_UPDATE listener!
    }

    public void close() {
        plugin.log("Closing jedis connection...");
        connection.close();
    }

    public abstract S wrap(ServerPlugin plugin);

    public <T> void sendRequest(ServerChannel channel, T message) {
        JsonElement jsonMessage = serializer.serialize(message);
        connection.sendRequest(channel, jsonMessage.toString());
    }

    public void registerServer(Server server) {
        String id = server.getId();
        servers.put(id.toLowerCase(Locale.ROOT), gameClazz.cast(server));
    }

    public Collection<S> getServers() {
        return servers.values();
    }
}
