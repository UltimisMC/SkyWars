package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public interface Game {

    void prepareUser(User user);

    void quitUser(User user);
}
