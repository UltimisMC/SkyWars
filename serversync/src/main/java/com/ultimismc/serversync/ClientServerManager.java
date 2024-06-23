package com.ultimismc.serversync;

import com.ultimismc.serversync.communication.ConnectionData;
import com.ultimismc.serversync.communication.ServerChannel;
import com.ultimismc.serversync.communication.ServerChannelConstants;
import com.ultimismc.serversync.communication.listener.ServerFetchMessage;
import lombok.Getter;

import java.util.Timer;

/**
 * @author DirectPlan
 */
public abstract class ClientServerManager<T extends Server> extends ServerManager<ServerPlugin, T> {

    @Getter private T server;

    public ClientServerManager(ServerPlugin plugin, String serverCategory, Class<T> gameClazz) {
        super(plugin, serverCategory, gameClazz);
    }

    @Override
    public void connect(ConnectionData connectionData) {
        server = wrap(software);

        super.connect(connectionData);

        log("Broadcasting server data...");

        log("Starting heartbeat updater...");
        startHeartbeatUpdater();
        sendServerMessage(ServerChannelConstants.SERVER_BOOT);
    }


    @Override
    public void close() {
        sendServerMessage(ServerChannelConstants.SERVER_SHUTDOWN);
        super.close();
    }

    public abstract T wrap(ServerPlugin plugin);

    @Override
    public void onConnected() {
        subscribeToAllChannels();
        subscribeChannel(new ServerFetchMessage(this));
    }

    public void startHeartbeatUpdater() {
        Timer timer = new Timer();
        long serverTimeout = 5000L;
        ServerHeartbeatUpdater<T> heartbeatUpdater = new ServerHeartbeatUpdater<>(server, this, serverTimeout);
        timer.scheduleAtFixedRate(heartbeatUpdater, 150L, 1000L);
    }

    public void sendServerUpdate() {
        sendServerMessage(ServerChannelConstants.SERVER_UPDATE);
    }

    public void sendServerMessage(ServerChannel serverChannel) {
        sendRequest(serverChannel, server);
        if(serverChannel.isUpdate()) sendServerUpdate();
    }

    public String getServerId() {
        return server.getId();
    }
}
