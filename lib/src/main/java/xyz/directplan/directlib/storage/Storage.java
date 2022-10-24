package xyz.directplan.directlib.storage;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.directplan.directlib.storage.misc.ConnectionCredentials;

import java.util.logging.Level;

/**
 * @author DirectPlan
 */

public class Storage {

    private final JavaPlugin plugin;

    private StorageConnection storageConnection;
    private final ConnectionCredentials credentials;

    public Storage(JavaPlugin plugin, ConnectionCredentials credentials) {
        this.plugin = plugin;
        this.credentials = credentials;
    }

    public void connect(StorageConnection storageConnection) {
        this.connect(storageConnection, credentials);
    }

    public void connect(StorageConnection storageConnection, ConnectionCredentials credentials) {
        this.storageConnection = storageConnection;
        if(credentials != null) {
            storageConnection.setCredentials(credentials);
        }

        String storageName = storageConnection.getName();
        plugin.getLogger().info("Using " + storageName + " database for storing game data.");

        plugin.getLogger().log(Level.WARNING, "Connecting to the " + storageName + " database...");
        storageConnection.connect();
    }

    public void close() {
        if(storageConnection == null) return;
        storageConnection.close();
    }
}