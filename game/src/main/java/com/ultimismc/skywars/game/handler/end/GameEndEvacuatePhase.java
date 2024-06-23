package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.handler.team.GameTeam;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public class GameEndEvacuatePhase extends GameEndPhase {

    public GameEndEvacuatePhase(GameHandler gameHandler) {
        super(gameHandler, 3);
    }

    @Override
    public void executePhase(GameTeam winnerTeam, Collection<GameTeam> gameTeams) {
        gameHandler.evacuateServer();
    }
}
