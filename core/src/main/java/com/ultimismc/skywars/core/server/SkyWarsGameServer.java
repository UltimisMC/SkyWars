package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.GameServer;
import com.ultimismc.gamescaler.ServerPlugin;

/**
 * @author DirectPlan
 */
public class SkyWarsGameServer extends GameServer {

    public SkyWarsGameServer(ServerPlugin plugin, String displayName, String id, boolean lobby) {
        super(plugin, displayName, id, lobby);
    }
}
