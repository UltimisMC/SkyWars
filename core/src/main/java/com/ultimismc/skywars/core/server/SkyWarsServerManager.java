package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.GameServer;
import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.ServerPlugin;
import com.ultimismc.skywars.core.SkyWarsPlugin;

/**
 * @author DirectPlan
 */
public class SkyWarsServerManager extends ServerManager<SkyWarsGameServer> {

    public SkyWarsServerManager(SkyWarsPlugin plugin) {
        super(new SkyWarsPluginWrapper(plugin), SkyWarsGameServer.class);
    }

    @Override
    public SkyWarsGameServer wrap(ServerPlugin plugin, String displayName, String id, boolean lobby) {
        return new SkyWarsGameServer(plugin, displayName, id, lobby);
    }
}
