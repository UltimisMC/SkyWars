package com.ultimismc.gamescaler.communication;

/**
 * @author DirectPlan
 */
public enum ServerChannelConstants implements ServerChannel {

    TEST,
    FETCH_SERVER,
    SERVER_UPDATE;

    @Override
    public String getName() {
        return name();
    }
}
