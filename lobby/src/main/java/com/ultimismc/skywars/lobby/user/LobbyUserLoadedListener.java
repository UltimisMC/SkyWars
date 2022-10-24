package com.ultimismc.skywars.lobby.user;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserLoadedListener;
import com.ultimismc.skywars.lobby.LobbyManager;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LobbyUserLoadedListener implements UserLoadedListener {

    private final LobbyManager lobbyManager;

    @Override
    public void onUserLoaded(User user) {
        lobbyManager.handleJoin(user);
    }
}
