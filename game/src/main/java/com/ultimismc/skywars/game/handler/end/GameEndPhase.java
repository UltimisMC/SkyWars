package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.handler.team.GameTeam;
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
     * @param winnerTeam Game winner team
     * @param gameTeams Game teams
     */
    public abstract void executePhase(GameTeam winnerTeam, Collection<GameTeam> gameTeams);
}
