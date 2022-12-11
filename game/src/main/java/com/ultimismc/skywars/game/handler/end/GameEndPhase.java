package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public abstract class GameEndPhase {

    protected final GameHandler gameHandler;
    private final int executeIn;

    /**
     * Execute phase function
     *
     * @param winner Game winner
     * @param participators Game participators
     */
    public abstract void executePhase(UserGameSession winner, Collection<UserGameSession> participators);
}
