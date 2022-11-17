package com.ultimismc.skywars.game.handler.runnable;

import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.handler.GameHandler;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.config.replacement.Replacement;

/**
 * @author DirectPlan
 */
public class GamePreparer implements Runnable {

    private final GameHandler gameHandler;

    public GamePreparer(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    @Override
    public void run() {
        long preparerCountdown = gameHandler.getPrepareCountdownLeft();
        int seconds = (int) preparerCountdown / 1000;

        if(seconds == 0) {
            gameHandler.startGame();
            return;
        }
        ChatColor timeColor = ChatColor.GREEN;
        if(seconds == 10) {
            timeColor = ChatColor.GOLD;
        }
        if(seconds <= 5) {
            timeColor = ChatColor.RED;
        }
        if(seconds == 15 || seconds == 10 || seconds <= 5) {
            gameHandler.broadcastFunction(user -> PluginUtility.playSound(user.getPlayer(), Sound.SUCCESSFUL_HIT));
            MessageConfigKeys.GAME_STARTS_IN_MESSAGE.broadcastMessage(
                    new Replacement("time", timeColor + Integer.toString(seconds)));
        }
        gameHandler.updateScoreboard();
        gameHandler.setPrepareCountdownLeft(preparerCountdown - 1000L);
    }
}
