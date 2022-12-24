package com.ultimismc.gamescaler;

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
public class ServerManager {

    private final Map<String, GameServer> gameServers = new HashMap<>();

    private final ServerPlugin plugin;

    private final JedisConnection connection;
    private final Serializer serializer;

    public ServerManager(ServerPlugin plugin, Serializer serializer) {
        this.plugin = plugin;
        this.serializer = serializer;
        this.connection = new JedisConnection();
    }

    public ServerManager(ServerPlugin plugin) {
        this(plugin, new GsonSerializer());
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
        connection.subscribe(ServerChannelConstants.SERVER_UPDATE, new ChannelUpdateListener(this));


        plugin.log("Broadcasting server data...");

        GameServer gameServer = new GameServer(plugin, displayName, id, lobby);

    }

    public void connect(ConnectionData connectionData, String displayName, String id) {
        connect(connectionData, displayName, id, false);
    }

    public void registerGameServer(GameServer gameServer) {
        String id = gameServer.getId();
        gameServers.put(id.toLowerCase(Locale.ROOT), gameServer);
    }

    public Collection<GameServer> getServers() {
        return gameServers.values();
    }
}
