package com.ultimismc.serversync;

import com.google.gson.JsonElement;
import com.ultimismc.serversync.communication.ConnectionData;
import com.ultimismc.serversync.communication.JedisConnection;
import com.ultimismc.serversync.communication.ServerChannel;
import com.ultimismc.serversync.communication.ServerChannelConstants;
import com.ultimismc.serversync.communication.listener.*;
import com.ultimismc.serversync.serializer.GsonSerializer;
import com.ultimismc.serversync.serializer.Serializer;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ServerManager<P extends ServerSoftware, S extends Server> {

    private final Map<String, S> servers = new HashMap<>();

    protected final P software;
    private final String serverCategory;

    private final JedisConnection connection;
    private final Serializer serializer;

    protected final Class<S> gameClazz;

    public ServerManager(P software, String serverCategory, Serializer serializer, Class<S> gameClazz) {
        this.software = software;
        this.serverCategory = serverCategory;
        this.serializer = serializer;
        this.gameClazz = gameClazz;
        this.connection = new JedisConnection(software);
    }

    public ServerManager(P software, String serverCategory, Class<S> gameClazz) {
        this(software, serverCategory, new GsonSerializer(), gameClazz);
    }


    public void connect(ConnectionData connectionData) {
        // Establish connection.

        log("Establishing connection with Redis server...");
        if(!connection.establishConnection(connectionData)) {
            software.shutdown("Could not connect to redis server.");
            return;
        }
        log("Connection with Redis server has been established!");
        onConnected();
        fetchAllServers();
//        software.log("Broadcasting server data...");
//        server = wrap(plugin);
//        sendServerUpdate();
        // If everything is working correctly. The server should be registered from SERVER_UPDATE listener!
    }

    public void subscribeChannel(String category, ServerMessage<?> serverListener) {
        ServerChannel channel = serverListener.getChannel();
        String channelName = category + "-" + channel.getName();
        log("[" + category + "] Subscribing to " + channel.getName() + " channel...");
        connection.subscribe(channelName, serverListener);
    }

    public void subscribeChannel(ServerMessage<?> serverListener) {
        subscribeChannel(serverCategory, serverListener);
    }

    public void subscribeToAllChannels() {
        subscribeChannel(new UpdateChannelMessage<>(this));
        subscribeChannel(new TestChannelMessage(this));
        subscribeChannel(new ServerShutdownMessage(this));
        subscribeChannel(new ServerHeartbeatMessage<>(this));
    }

    public void close() {
        log("Toubkal will no longer maintain available game servers. Data shared with the servers will no longer be updated.");
        log("Closing redis connection...");
        connection.close();
    }


    public abstract void onConnected();

    public <T> void sendRequest(ServerChannel channel, T message) {
        JsonElement jsonMessage = serializer.serialize(message);
        String channelName = serverCategory + "-" + channel.getName();
        connection.sendRequest(channelName, jsonMessage.toString());
    }

    public void fetchServer(String serverId) {
        log("Sending a fetch request to " + serverId + "...");
        sendRequest(ServerChannelConstants.FETCH_SERVER, serverId);
    }

    public void fetchAllServers() {
        fetchServer("ALL");
    }

    public void updateServer(Server updatedServer) {
        String id = updatedServer.getId();

        S server = servers.computeIfAbsent(id, s -> gameClazz.cast(updatedServer));

        server.setOnlinePlayers(updatedServer.getOnlinePlayers());
        server.setMaximumPlayers(updatedServer.getMaximumPlayers());
        server.setWhitelisted(updatedServer.isWhitelisted());
        server.setLobby(updatedServer.isLobby());
        server.setLastHeartbeat(System.currentTimeMillis());

        server.updateVariables(updatedServer);
    }

    public S getServer(String id) {
        return servers.get(id);
    }

    public void log(String message) {
        software.log("[" + serverCategory + "] " + message);
    }
    public void removeServer(Server server) {
        servers.remove(server.getId());
    }

    public Collection<S> getServers() {
        return servers.values();
    }

    public boolean isConnected() {
        return connection.isConnected();
    }
}
