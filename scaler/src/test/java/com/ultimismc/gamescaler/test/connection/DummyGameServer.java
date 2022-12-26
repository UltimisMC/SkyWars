package com.ultimismc.gamescaler.test.connection;

import com.ultimismc.gamescaler.Server;
import com.ultimismc.gamescaler.ServerPlugin;

/**
 * @author DirectPlan
 */
public class DummyGameServer extends Server {

    public DummyGameServer(ServerPlugin plugin, String displayName, String id, boolean lobby) {
        super(plugin, displayName, id, lobby);
    }

    @Override
    public void updateVariables(Server other) {

    }
}
