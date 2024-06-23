package com.ultimismc.serversync.communication;

import lombok.Getter;

/**
 * @author DirectPlan
 */
public enum ServerChannelConstants implements ServerChannel {

    TEST,
    FETCH_SERVER,
    SERVER_UPDATE,
    HEART_BEAT,

    SERVER_PLAYERS_UPDATED(true),
    SERVER_STATE_UPDATED(true),
    SERVER_BOOT(true),
    SERVER_SHUTDOWN;

    @Getter private final boolean update;

    ServerChannelConstants(boolean update) {
        this.update = update;
    }

    ServerChannelConstants() {
        this(false);
    }
    @Override
    public String getName() {
        return name();
    }
}
