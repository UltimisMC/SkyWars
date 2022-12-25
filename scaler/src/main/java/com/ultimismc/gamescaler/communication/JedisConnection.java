package com.ultimismc.gamescaler.communication;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.CompletableFuture;

/**
 * @author DirectPlan
 */
public class JedisConnection {

    private JedisPool jedisPool;

    public synchronized boolean establishConnection(ConnectionData connectionData) {
        String host = connectionData.getHost();
        int port = connectionData.getPort();

        jedisPool = new JedisPool(host, port);
        try (Jedis jedis = jedisPool.getResource()) {
            String password = connectionData.getPassword();
            if(password != null) {
                String response = jedis.auth(password);
                System.out.println("Jedis Authentication Response: " + response);
            }
            return jedis.isConnected();
        }
    }

    public void subscribe(String channel, JedisPubSub pubSub) {
        System.out.println("Subscribing to " + channel + " channel.");
        synchronized (this) {
            Thread thread = new Thread(() -> {
                try (Jedis jedis = jedisPool.getResource()) {
                    jedis.auth("N9jvuUwBTKg2INJsEhJXt4JbnMNsFJjH");
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
                jedis.auth("N9jvuUwBTKg2INJsEhJXt4JbnMNsFJjH");
                jedis.set(key, value);
            }
        });
    }

    public CompletableFuture<String> get(String key) {
        return CompletableFuture.supplyAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.auth("N9jvuUwBTKg2INJsEhJXt4JbnMNsFJjH");
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
