package com.ultimismc.skywars.lobby.user;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserLoadedListener;
import com.ultimismc.skywars.lobby.LobbyManager;

/**
 * @author DirectPlan
 */
public class LobbyUserLoadedListener extends UserLoadedListener {

    private final LobbyManager lobbyManager;

    public LobbyUserLoadedListener(LobbyManager lobbyManager, SkyWarsPlugin plugin) {
        super(plugin);
        this.lobbyManager = lobbyManager;
    }

    @Override
    public void onUserLoaded(User user) {
        super.onUserLoaded(user);

        lobbyManager.handleJoin(user);
    }
}
