package com.ultimismc.skywars.core.storage;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.storage.Storage;
import xyz.directplan.directlib.storage.misc.ConnectionCredentials;

import java.util.UUID;

/**
 * @author DirectPlan
 */
public class UserStorage {

    private final Storage storage;
    private final MongoStorageConnection mongoStorageConnection;

    public UserStorage(SkyWarsPlugin plugin) {

        String host = ConfigKeys.STORAGE_HOST.getStringValue();
        int port = ConfigKeys.STORAGE_PORT.getInteger();
        if(port == 0) {
            port = 3306;
        }
        String username = ConfigKeys.STORAGE_USERNAME.getStringValue();
        String password = ConfigKeys.STORAGE_PASSWORD.getStringValue();

        String database = ConfigKeys.STORAGE_DATABASE.getStringValue();

        ConnectionCredentials credentials = new ConnectionCredentials(host, username, password, database, port, 0);
        mongoStorageConnection = new MongoStorageConnection(plugin, credentials);
        storage = new Storage(plugin, credentials);
    }

    public void connect() {
        storage.connect(mongoStorageConnection);
    }

    public void close() {
        storage.close();
    }

    public User loadUser(UUID uuid) {
        return mongoStorageConnection.loadUser(uuid);
    }

    public void saveUser(User user) {
        mongoStorageConnection.saveUser(user);
    }
}
