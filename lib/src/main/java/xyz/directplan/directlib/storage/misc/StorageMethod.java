package xyz.directplan.directlib.storage.misc;

import lombok.Getter;

/**
 * @author DirectPlan
 */
public enum StorageMethod {

    MYSQL(3306),
    MONGODB(27017);

    @Getter private final int port;

    StorageMethod(int port) {
        this.port = port;
    }

}
