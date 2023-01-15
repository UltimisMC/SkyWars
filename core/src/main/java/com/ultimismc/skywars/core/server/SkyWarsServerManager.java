package com.ultimismc.skywars.core.server;

import com.ultimismc.serversync.ClientServerManager;
import com.ultimismc.serversync.Server;
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
    
    public SkyWarsServer getPerfectLobby() {
        Stream<SkyWarsServer> stream = getServers().stream().filter(Server::isLobby).filter(server -> !server.isFull());
        Optional<SkyWarsServer> perfectLobby = stream.max((o1, o2) -> Integer.compare(o2.getOnlinePlayers(), o1.getMaximumPlayers()));
        return perfectLobby.orElse(null);
    }

    public void sendToLobby(User user) {
        SkyWarsServer perfectLobby = getPerfectLobby();
        sendToServer(user, perfectLobby);
    }
    
    public boolean sendToAvailableServer(User user, TeamType teamType, GameType gameType, String map) {
        SkyWarsServer server = getPerfectAvailableServer(teamType, gameType, map);
        if(server == null) {
            user.sendMessage("&cThere are no available game servers at this moment.");
            return false;
        }
        sendToServer(user, server);
        return true;
    }

    public boolean sendToAvailableServer(User user, TeamType teamType, GameType gameType) {
        return sendToAvailableServer(user, teamType, gameType, null);
    }

    public boolean sendToAvailableServer(User user, GameConfig gameConfig) {
        return sendToAvailableServer(user, gameConfig.getTeamType(), gameConfig.getGameType());
    }

    public void sendToServer(User user, SkyWarsServer server) {
        user.sendMessage("&aSending you to " + server.getId() + "!");

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
