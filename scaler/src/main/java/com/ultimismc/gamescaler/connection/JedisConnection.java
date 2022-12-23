package com.ultimismc.gamescaler.connection;

import redis.clients.jedis.Jedis;

/**
 * @author DirectPlan
 */
public class JedisConnection {

    private final ConnectionData connectionData;

    private Jedis jedis;

    public JedisConnection(ConnectionData connectionData) {
        this.connectionData = connectionData;

    }
    public JedisConnection(String host, int port) {
        this(new ConnectionData(host, port));
    }

    public JedisConnection(String host) {
        this(host, 6379);
    }

    public JedisConnection password(String password) {
        connectionData.setPassword(password);
        return this;
    }

    public void establishConnection() {
        String host = connectionData.getHost();
        int port = connectionData.getPort();

        jedis = new Jedis(host, port);
        String password = connectionData.getPassword();
        if(password != null) {
            jedis.auth(password);
        }
    }
}
