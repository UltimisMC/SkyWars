package com.ultimismc.gamescaler.communication;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author DirectPlan
 */
public class JedisConnection {

    private Jedis jedis;

    public synchronized boolean establishConnection(ConnectionData connectionData) {
        String host = connectionData.getHost();
        int port = connectionData.getPort();

        jedis = new Jedis(host, port);
        String password = connectionData.getPassword();
        if(password != null) {
            jedis.auth(password);
        }
        return jedis.isConnected();
    }

    public void subscribe(String channel, JedisPubSub pubSub) {
        jedis.subscribe(pubSub, channel);
    }

    public void subscribe(ServerChannel channel, JedisPubSub pubSub) {
        subscribe(channel.getName(), pubSub);
    }

    public void sendRequest(ServerChannel channel, String message) {
        jedis.publish(channel.getName(), message);
    }
}
