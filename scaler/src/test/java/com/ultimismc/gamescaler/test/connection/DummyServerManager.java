package com.ultimismc.gamescaler.test.connection;

import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.ServerPlugin;
import com.ultimismc.gamescaler.test.DummyServerPlugin;

/**
 * @author DirectPlan
 */
public class DummyServerManager extends ServerManager<DummyGameServer> {

    public DummyServerManager() {
        super(new DummyServerPlugin(), DummyGameServer.class);
    }

    @Override
    public DummyGameServer wrap(ServerPlugin plugin) {
        return new DummyGameServer(plugin, "Test", "TEST01", false);
    }
}
