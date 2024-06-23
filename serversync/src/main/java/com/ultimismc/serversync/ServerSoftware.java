package com.ultimismc.serversync;

import java.util.logging.Level;

/**
 * @author DirectPlan
 */
public interface ServerSoftware {

    String getName();

    void log(Level level, String message);

    default void log(String message) {
        log(Level.INFO, message);
    }
    void shutdown(String reason);
}
