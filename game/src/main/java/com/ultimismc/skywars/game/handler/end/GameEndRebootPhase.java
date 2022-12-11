package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
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
    public void executePhase(UserGameSession winner, Collection<UserGameSession> players) {


        // - Notify other servers about this change too, to not cause saving conflicts.
        // - Save all the participators to database
        // - Reboot the server
        String repeatLine = StringUtils.repeat("▬", 70);
        gameHandler.broadcastFunction(user -> {
            user.sendMessage(ChatColor.GREEN + repeatLine);
            user.sendMessage("                             &f&lSkyWars");
            user.sendMessage(" ");
            user.sendMessage(" &aUhh, you should sent you back to the lobby right?");
            user.sendMessage(" &aChange of plans, you are staying here forever!");
            user.sendMessage(" ");
            user.sendMessage(" &eWinner for this game is: " + winner.getDisplayName());
            user.sendMessage(" ");
            user.sendMessage(ChatColor.GREEN + repeatLine);
        });
        Bukkit.shutdown();
    }
}
