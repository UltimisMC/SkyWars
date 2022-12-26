package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.ServerPlugin;
import com.ultimismc.gamescaler.communication.ConnectionData;
import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.game.GameConfig;

/**
 * @author DirectPlan
 */

public class SkyWarsServerManager extends ServerManager<SkyWarsServer> {

    private final SkyWarsPlugin plugin;
    private final GameConfig gameConfig;

    public SkyWarsServerManager(SkyWarsPlugin plugin, GameConfig gameConfig) {
        super(new SkyWarsPluginWrapper(plugin), SkyWarsServer.class);

        this.plugin = plugin;
        this.gameConfig = gameConfig;
    }

    @Override
    public SkyWarsServer wrap(ServerPlugin plugin) {
        return new SkyWarsServer(plugin, gameConfig);
    }

    public void connect() {
        String host = ConfigKeys.JEDIS_HOST.getStringValue();
        int port = ConfigKeys.JEDIS_PORT.getInteger();
        String password = ConfigKeys.JEDIS_PASSWORD.getStringValue();

        connect(new ConnectionData(host, port, password));

        if(isConnected()) {
            plugin.registerListeners(new ServerUpdateListener(this));
        }
    }
}
