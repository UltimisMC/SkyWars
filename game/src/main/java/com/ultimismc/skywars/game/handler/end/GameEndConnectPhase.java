package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.core.game.GameState;
import com.ultimismc.skywars.game.handler.team.GameTeam;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public class GameEndConnectPhase extends GameEndPhase {

    private final SkyWarsServerManager serverManager;

    public GameEndConnectPhase(SkyWarsPlugin plugin, GameHandler gameHandler) {
        super(gameHandler, 3);

        serverManager = plugin.getServerManager();
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
