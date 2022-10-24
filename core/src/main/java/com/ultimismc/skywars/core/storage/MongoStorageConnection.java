package com.ultimismc.skywars.core.storage;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.storage.StorageConnection;
import xyz.directplan.directlib.storage.misc.ConnectionCredentials;

import java.util.UUID;

/**
 * @author DirectPlan
 */
public class MongoStorageConnection extends StorageConnection {

    private final SkyWarsPlugin plugin;

    public MongoStorageConnection(SkyWarsPlugin plugin, ConnectionCredentials credentials) {
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
