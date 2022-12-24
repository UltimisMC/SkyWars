package com.ultimismc.gamescaler.communication;

/**
 * @author DirectPlan
 */
public enum ServerChannelConstants implements ServerChannel {

    FETCH_SERVER(),
    SERVER_UPDATE();

    @Override
    public String getName() {
        return name();
    }
}
