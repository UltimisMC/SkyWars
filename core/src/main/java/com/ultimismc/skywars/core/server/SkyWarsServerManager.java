package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.connection.ConnectionData;

public class SkyWarsServerManager extends ServerManager<SkyWarsServer> {

    public SkyWarsServerManager(ConnectionData connectionData) {
        super(connectionData);
    }

    public SkyWarsServerManager(String host, int port) {
        super(host, port);
    }
}
