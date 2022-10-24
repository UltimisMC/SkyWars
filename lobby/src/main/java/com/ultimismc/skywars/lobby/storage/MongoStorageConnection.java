package com.ultimismc.skywars.lobby.storage;

import com.ultimismc.skywars.lobby.SkyWarsLobbyPlugin;
import com.ultimismc.skywars.lobby.user.User;
import xyz.directplan.directlib.storage.StorageConnection;
import xyz.directplan.directlib.storage.misc.ConnectionCredentials;

import java.util.UUID;

/**
 * @author DirectPlan
 */
public class MongoStorageConnection extends StorageConnection {

    private final SkyWarsLobbyPlugin plugin;

    public MongoStorageConnection(SkyWarsLobbyPlugin plugin, ConnectionCredentials credentials) {
        super("MongoDB", credentials);

        this.plugin = plugin;
    }

    @Override
    public void connect() {

    }

    public User loadUser(UUID uuid) {
        return new User(uuid);
    }

    public void saveUser(User user) {

    }

    @Override
    public void close() {

    }
}
