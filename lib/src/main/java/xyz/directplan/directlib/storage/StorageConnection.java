package xyz.directplan.directlib.storage;

import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.storage.misc.ConnectionCredentials;

/**
 * @author DirectPlan
 */
@Getter
public abstract class StorageConnection {

    protected final String name;
    @Setter protected ConnectionCredentials credentials;

    public StorageConnection(String name, ConnectionCredentials credentials) {
        this.name = name;
        this.credentials = credentials;

    }

    public abstract void connect();

    public abstract void close();
}
