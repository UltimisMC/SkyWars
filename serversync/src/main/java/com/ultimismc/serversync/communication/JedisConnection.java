package com.ultimismc.serversync.communication;

import com.ultimismc.serversync.ServerSoftware;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class JedisConnection {

    private final ServerSoftware software;
    private JedisPool jedisPool;

    private final ExecutorService service = Executors.newFixedThreadPool(10);

    public synchronized boolean establishConnection(ConnectionData connectionData) {
        String host = connectionData.getHost();
        int port = connectionData.getPort();

        String password = connectionData.getPassword();
        jedisPool = new JedisPool(host, port, "default", password);
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.isConnected();
        }
    }

    public synchronized void subscribe(String channel, JedisPubSub pubSub) {

        synchronized (this) {
            CompletableFuture.runAsync(() -> {
                try (Jedis jedis = jedisPool.getResource()) {
                    jedis.subscribe(pubSub, channel);
                }catch (Exception e) {
                    software.log("An error has occurred whilst subscribing to " + channel + ": " + e.getMessage());
                }
            }, service);
        }
    }

    public void subscribe(ServerChannel channel, JedisPubSub pubSub) {
        subscribe(channel.getName(), pubSub);
    }

    public void set(String key, String value) {
        CompletableFuture.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.set(key, value);
            }
        });
    }

    public CompletableFuture<String> get(String key) {
        return CompletableFuture.supplyAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                return jedis.get(key);
            }
        });
    }


    public void sendRequest(String channelName, String message) {
        CompletableFuture.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.publish(channelName, message);
            }
        });
    }
    public void sendRequest(ServerChannel channel, String message) {
        sendRequest(channel.getName(), message);
    }

    public boolean isConnected() {
        return !jedisPool.isClosed();
    }

    public void close() {
        jedisPool.close();
    }
}
