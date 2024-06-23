package com.ultimismc.serversync.communication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class ConnectionData {

    private final String host;
    private final int port;
    @Setter private String password;

    public ConnectionData(String host, int port, String password) {
        this(host, port);
        this.password = password;
    }
}
