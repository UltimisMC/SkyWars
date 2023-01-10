package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.core.game.GameState;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.handler.team.GameTeam;
import org.bukkit.Bukkit;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public class GameEndRestartPhase extends GameEndPhase {

    public GameEndRestartPhase(GameHandler gameHandler) {
        super(gameHandler, 3);
    }

    @Override
    public void executePhase(GameTeam winnerTeam, Collection<GameTeam> gameTeams) {
        gameHandler.setGameState(GameState.RESTARTING);
        Bukkit.shutdown();
    }
}
