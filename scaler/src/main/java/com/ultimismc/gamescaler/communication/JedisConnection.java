package com.ultimismc.gamescaler.communication;

import com.ultimismc.gamescaler.ServerPlugin;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class JedisConnection {

    private final ServerPlugin plugin;
    private JedisPool jedisPool;

    public synchronized boolean establishConnection(ConnectionData connectionData) {
        String host = connectionData.getHost();
        int port = connectionData.getPort();

        String password = connectionData.getPassword();
        jedisPool = new JedisPool(host, port, "default", password);
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.isConnected();
        }
    }

    public void subscribe(String channel, JedisPubSub pubSub) {
        plugin.log("Subscribing to " + channel + " channel.");
        synchronized (this) {
            Thread thread = new Thread(() -> {
                try (Jedis jedis = jedisPool.getResource()) {
                    jedis.subscribe(pubSub, channel);
                }
            });
            thread.setName("Game Scaler - Jedis Subscriber " + channel + " Channel");
            thread.setDaemon(true);
            thread.start();
        }
    }

    public synchronized void subscribe(ServerChannel channel, JedisPubSub pubSub) {
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


    public void sendRequest(ServerChannel channel, String message) {
        CompletableFuture.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.publish(channel.getName(), message);
            }
        });
    }

    public void close() {
        jedisPool.close();
    }
}
