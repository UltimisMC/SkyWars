package com.ultimismc.gamescaler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class GameServer {

    private final String displayName;
    private final String id;

    private int maximumPlayers;
    private ServerState state;
    private int onlinePlayers;
    private boolean whitelisted;

    public boolean isIdling() { // Or Waiting
        return state == ServerState.IDLING;
    }

    public boolean isStarting() {
        return state == ServerState.STARTING;
    }

    public boolean hasStarted() {
        return state == ServerState.STARTED;
    }
}
