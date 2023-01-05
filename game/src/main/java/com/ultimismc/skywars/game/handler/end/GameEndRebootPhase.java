package com.ultimismc.skywars.game.handler.end;

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
public class GameEndRebootPhase extends GameEndPhase {

    public GameEndRebootPhase(GameHandler gameHandler) {
        super(gameHandler, 3);
    }

    @Override
    public void executePhase(GameTeam winnerTeam, Collection<GameTeam> teams) {


        // - Notify other servers about this change too, to not cause saving conflicts.
        // - Save all the participators to database
        // - Reboot the server

        gameHandler.setGameState(GameState.RESTARTING);
        String repeatLine = StringUtils.repeat("▬", 70);
        gameHandler.broadcastFunction(user -> {
            user.sendMessage(ChatColor.GREEN + repeatLine);
            user.sendMessage("  ");
            user.sendMessage("         &aRestarting &e" + gameHandler.getServerId() + "&a...");
            user.sendMessage("  ");
            user.sendMessage(ChatColor.GREEN + repeatLine);
        });
        Bukkit.shutdown();
    }
}
