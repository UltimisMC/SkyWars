package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.skywars.core.SkyWarsPlugin;

/**
 * @author DirectPlan
 */
public class SkyWarsServerManager extends ServerManager {

    public SkyWarsServerManager(SkyWarsPlugin plugin) {
        super(new SkyWarsPluginWrapper(plugin));
    }
}
