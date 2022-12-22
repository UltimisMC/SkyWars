package com.ultimismc.gamescaler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ServerManager<G extends GameServer> {

    private final Map<String, G> gameServers = new HashMap<>();

    public void registerGameServer(G gameServer) {
        String name = gameServer.getName();
        gameServers.put(name.toLowerCase(Locale.ROOT), gameServer);
    }

    public Collection<G> getServers() {
        return gameServers.values();
    }
}
