package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.handler.team.GameTeam;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public class GameEndConnectPhase extends GameEndPhase {

    private final SkyWarsServerManager serverManager;

    public GameEndConnectPhase(GameHandler gameHandler) {
        super(gameHandler, 3);

        serverManager = gameHandler.getServerManager();
    }

    @Override
    public void executePhase(GameTeam winnerTeam, Collection<GameTeam> teams) {
        gameHandler.broadcastFunction(userGameSession -> {
            User user = userGameSession.getUser();
            if(!user.isOnline()) return;

            if(serverManager.sendToAvailableServer(user, gameHandler.getGameConfig())) return; // A server was found! Not sending to lobby...
            serverManager.sendToLobby(user);
        });
    }
}
