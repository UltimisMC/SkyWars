package com.ultimismc.gamescaler;

import com.google.gson.JsonElement;
import com.ultimismc.gamescaler.communication.ServerChannel;
import com.ultimismc.gamescaler.communication.ConnectionData;
import com.ultimismc.gamescaler.communication.JedisConnection;
import com.ultimismc.gamescaler.communication.ServerChannelConstants;
import com.ultimismc.gamescaler.communication.listener.ServerListener;
import com.ultimismc.gamescaler.communication.listener.ServerRemoveChannelListener;
import com.ultimismc.gamescaler.communication.listener.TestChannelListener;
import com.ultimismc.gamescaler.communication.listener.UpdateChannelListener;
import com.ultimismc.gamescaler.serializer.GsonSerializer;
import com.ultimismc.gamescaler.serializer.Serializer;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ServerManager<S extends Server> {

    private final Map<String, S> servers = new HashMap<>();

    private final ServerPlugin plugin;

    private final JedisConnection connection;
    private final Serializer serializer;

    private final Class<S> gameClazz;

    private S server;

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
        plugin.log("Subscribing to server channels...");
        subscribeChannel(ServerChannelConstants.SERVER_UPDATE, new UpdateChannelListener<>(this));
        subscribeChannel(ServerChannelConstants.TEST, new TestChannelListener(this));
        subscribeChannel(ServerChannelConstants.SERVER_REMOVE, new ServerRemoveChannelListener(this));

        plugin.log("Broadcasting server data...");
        server = wrap(plugin);
        sendServerUpdate();
        // If everything is working correctly. The server should be registered from SERVER_UPDATE listener!
    }

    public void subscribeChannel(ServerChannel channel, ServerListener<?> serverListener) {
        plugin.log("Subscribing to " + channel + " channel...");
        connection.subscribe(channel, serverListener);
    }

    public void close() {
        plugin.log("Broadcasting remove signal...");
        sendRequest(ServerChannelConstants.SERVER_REMOVE, server);
        plugin.log("Closing jedis connection...");
        connection.close();
    }

    public abstract S wrap(ServerPlugin plugin);

    public <T> void sendRequest(ServerChannel channel, T message) {
        JsonElement jsonMessage = serializer.serialize(message);
        connection.sendRequest(channel, jsonMessage.toString());
    }

    public void sendServerUpdate() {
        sendRequest(ServerChannelConstants.SERVER_UPDATE, server);
    }

    public void updateServer(Server updatedServer) {
        String id = updatedServer.getId();

        S server = servers.computeIfAbsent(id, s -> gameClazz.cast(updatedServer));

        server.setOnlinePlayers(updatedServer.getOnlinePlayers());
        server.setMaximumPlayers(updatedServer.getMaximumPlayers());
        server.setWhitelisted(updatedServer.isWhitelisted());
        server.setLobby(updatedServer.isLobby());

        server.updateVariables(updatedServer);
    }

    public void removeServer(Server server) {
        servers.remove(server.getId());
    }

    public Collection<S> getServers() {
        return servers.values();
    }

    public boolean isConnected() {
        return connection.isConnected();
    }
}
