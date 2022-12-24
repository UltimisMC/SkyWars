package com.ultimismc.gamescaler;

import com.google.gson.JsonElement;
import com.ultimismc.gamescaler.communication.ServerChannel;
import com.ultimismc.gamescaler.communication.listener.ChannelUpdateListener;
import com.ultimismc.gamescaler.communication.ConnectionData;
import com.ultimismc.gamescaler.communication.JedisConnection;
import com.ultimismc.gamescaler.communication.ServerChannelConstants;
import com.ultimismc.gamescaler.serializer.GsonSerializer;
import com.ultimismc.gamescaler.serializer.Serializer;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Getter
public abstract class ServerManager<G extends GameServer> {

    private final Map<String, G> gameServers = new HashMap<>();

    private final ServerPlugin plugin;

    private final JedisConnection connection;
    private final Serializer serializer;

    private final Class<G> gameClazz;

    public ServerManager(ServerPlugin plugin, Serializer serializer, Class<G> gameClazz) {
        this.plugin = plugin;
        this.serializer = serializer;
        this.gameClazz = gameClazz;
        this.connection = new JedisConnection();
    }

    public ServerManager(ServerPlugin plugin, Class<G> gameClazz) {
        this(plugin, new GsonSerializer(), gameClazz);
    }


    public void connect(ConnectionData connectionData, String displayName, String id, boolean lobby) {
        // Establish connection.

        plugin.log("Establishing connection with Redis server...");
        if(!connection.establishConnection(connectionData)) {
            plugin.shutdown("Could not connect to redis server.");
            return;
        }
        plugin.log("Connection with Redis server has been established!");
        plugin.log("Subscribing to server channels...");
        connection.subscribe(ServerChannelConstants.SERVER_UPDATE, new ChannelUpdateListener<>(this));


        plugin.log("Broadcasting server data...");

        GameServer gameServer = new GameServer(plugin, displayName, id, lobby);
        sendRequest(ServerChannelConstants.SERVER_UPDATE, gameServer);
        // If everything is working correctly. The server should be registered from SERVER_UPDATE listener!
    }

    public abstract G wrap(ServerPlugin plugin, String displayName, String id, boolean lobby);

    public <T> void sendRequest(ServerChannel channel, T message) {
        JsonElement jsonMessage = serializer.serialize(message);
        connection.sendRequest(channel, jsonMessage.toString());
    }

    public void connect(ConnectionData connectionData, String displayName, String id) {
        connect(connectionData, displayName, id, false);
    }

    public void registerGameServer(GameServer gameServer) {
        String id = gameServer.getId();
        gameServers.put(id.toLowerCase(Locale.ROOT), gameClazz.cast(gameServer));
    }

    public Collection<G> getServers() {
        return gameServers.values();
    }
}
