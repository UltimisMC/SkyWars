package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
@Setter
public abstract class AbstractGame implements Game {

    private final GameHandler gameHandler;

    private GameState gameState = GameState.WAITING;

    @Override
    public void prepareUser(User user) {

    }

    @Override
    public void quitUser(User user) {

    }

    @Override
    public int getPlayersLeft() {
        return 0;
    }
}
