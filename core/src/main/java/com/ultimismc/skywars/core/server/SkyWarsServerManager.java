package com.ultimismc.skywars.core.server;

import com.ultimismc.serversync.ClientServerManager;
import com.ultimismc.serversync.ServerPlugin;
import com.ultimismc.serversync.communication.ConnectionData;
import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.PluginUtility;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author DirectPlan
 */

public class SkyWarsServerManager extends ClientServerManager<SkyWarsServer> {

    private final SkyWarsPlugin plugin;
    private final GameConfig gameConfig;

    public SkyWarsServerManager(SkyWarsPlugin plugin, GameConfig gameConfig) {
        super(new SkyWarsPluginWrapper(plugin), "skywars", SkyWarsServer.class);

        this.plugin = plugin;
        this.gameConfig = gameConfig;
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

    @Override
    public SkyWarsServer wrap(ServerPlugin plugin) {
        return new SkyWarsServer(plugin, gameConfig);
    }

    public SkyWarsServer getPerfectAvailableServer(TeamType teamType, GameType gameType, String map) {
        Stream<SkyWarsServer> stream = getServers().stream().filter(server -> !server.isLobby()).filter(server -> !server.hasStarted() && !server.isFull());
        if(teamType != null) {
            stream = stream.filter(server -> server.getTeamType() == teamType);
        }
        if(gameType != null) {
            stream = stream.filter(server -> server.getGameType() == gameType);
        }
        if(map != null) {
            stream = stream.filter(server -> server.getMapName().equals(map));
        }
        Optional<SkyWarsServer> perfectServer = stream.max((o1, o2) -> Integer.compare(o2.getOnlinePlayers(), o1.getMaximumPlayers()));
        return perfectServer.orElse(null);
    }

    public void sendToAvailableServer(User user, TeamType teamType, GameType gameType, String map) {
        SkyWarsServer server = getPerfectAvailableServer(teamType, gameType, map);
        if(server == null) {
            user.sendMessage("&cThere are not game servers available at this moment. Please wait!");
            return;
        }
        user.sendMessage("&aSending you to " + server.getId() + "!");
        sendToGameServer(user, server);
    }

    public void sendToAvailableServer(User user, TeamType teamType, GameType gameType) {
        sendToAvailableServer(user, teamType, gameType, null);
    }

    public void sendToGameServer(User user, SkyWarsServer server) {
        String serverId = server.getId();
        PluginUtility.connectToServer(plugin, user.getPlayer(), serverId);
    }

    public int getOnlinePlayers(TeamType teamType, GameType gameType) {
        Collection<SkyWarsServer> servers = getGameServers(teamType, gameType);
        int onlinePlayers = 0;
        for(SkyWarsServer server : servers) {
            onlinePlayers += server.getOnlinePlayers();
        }
        return onlinePlayers;
    }

    public Collection<SkyWarsServer> getGameServers(TeamType teamType, GameType gameType) {
        return getServers().stream().filter(server -> !server.isLobby() &&
                (teamType == null || server.getTeamType() == teamType) &&
                (gameType == null || server.getGameType() == gameType)).collect(Collectors.toList());
    }
    public Collection<SkyWarsServer> getGameServers() {
        return getGameServers(null, null);
    }
}
