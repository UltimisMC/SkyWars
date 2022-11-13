package com.ultimismc.skywars.game.user;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserSavedListener;
import com.ultimismc.skywars.game.GameManager;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class GameUserSavedListener extends UserSavedListener {

    private final GameManager gameManager;

    @Override
    public void onUserSaved(User user) {
        super.onUserSaved(user);

        gameManager.handleQuit(user);
    }
}
