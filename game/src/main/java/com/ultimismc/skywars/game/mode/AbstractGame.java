package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.config.replacement.Replacement;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public abstract class AbstractGame implements Game {

    private final GameHandler gameHandler;

    @Override
    public void prepareUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.JOIN_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName),
                new Replacement("current-players", gameHandler.getOnlinePlayers()),
                new Replacement("maximum-players", gameHandler.getMaximumPlayers()));


    }

    @Override
    public void quitUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.QUIT_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName));
    }
}
