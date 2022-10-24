package com.ultimismc.skywars.lobby.user;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserSavedListener;
import com.ultimismc.skywars.lobby.LobbyManager;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LobbyUserSavedListener implements UserSavedListener {

    private final LobbyManager lobbyManager;

    @Override
    public void onUserSaved(User user) {
        lobbyManager.handleQuit(user);
    }
}
