package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.ServerPlugin;
import com.ultimismc.gamescaler.communication.ConnectionData;
import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.PluginUtility;

import java.util.Optional;
import java.util.stream.Stream;

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

    public SkyWarsServer getPerfectAvailableServer(TeamType teamType, GameType gameType) {
        Stream<SkyWarsServer> stream = getServers().stream().filter(skyWarsServer -> !skyWarsServer.hasStarted() && !skyWarsServer.isFull());
        if(teamType != null) {
            stream = stream.filter(skyWarsServer -> skyWarsServer.getTeamType() == teamType);
        }
        if(gameType != null) {
            stream = stream.filter(skyWarsServer -> skyWarsServer.getGameType() == gameType);
        }
        Optional<SkyWarsServer> perfectServer = stream.max((o1, o2) -> Integer.compare(o2.getOnlinePlayers(), o1.getMaximumPlayers()));
        return perfectServer.orElse(null);
    }

    public void sendToAvailableServer(User user, TeamType teamType, GameType gameType) {
        SkyWarsServer server = getPerfectAvailableServer(teamType, gameType);
        if(server == null) {
            user.sendMessage("&cThere are not game servers available at this moment. Please wait!");
            return;
        }
        user.sendMessage("&aSending you to " + server.getId() + "!");
        sendToGameServer(user, server);
    }

    public void sendToGameServer(User user, SkyWarsServer server) {
        String serverId = server.getId();
        PluginUtility.connectToServer(plugin, user.getPlayer(), serverId);
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
