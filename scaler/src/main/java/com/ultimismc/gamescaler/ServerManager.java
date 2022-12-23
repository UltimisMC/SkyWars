package com.ultimismc.gamescaler;

import com.ultimismc.gamescaler.connection.ConnectionData;
import com.ultimismc.gamescaler.connection.JedisConnection;
import redis.clients.jedis.Jedis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ServerManager<G extends GameServer> {

    private final Map<String, G> gameServers = new HashMap<>();

    private final JedisConnection connection;

    public ServerManager(ConnectionData connectionData) {
        this.connection = new JedisConnection(connectionData);
    }

    public ServerManager(String host, int port) {
        this.connection = new JedisConnection(host, port);
    }

    public void registerGameServer(G gameServer) {
        String id = gameServer.getId();
        gameServers.put(id.toLowerCase(Locale.ROOT), gameServer);
    }

    public Collection<G> getServers() {
        return gameServers.values();
    }
}
