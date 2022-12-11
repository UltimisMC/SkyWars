package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import com.ultimismc.skywars.game.user.UserRewardSummary;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;

import java.util.Collection;
import java.util.Map;

public class GameEndRewardsPhase extends GameEndPhase {

    public GameEndRewardsPhase(GameHandler gameHandler) {
        super(gameHandler, 3);
    }
    
    @Override
    public void executePhase(UserGameSession winner, Collection<UserGameSession> players) {
        String repeatLine = StringUtils.repeat("▬", 70);

        gameHandler.broadcastFunction(userGameSession -> {
            User user = userGameSession.getUser();
            UserRewardSummary rewardSummary = userGameSession.getRewardSummary();
            user.sendMessage(ChatColor.GREEN + repeatLine);
            user.sendMessage("                             &f&lReward Summary");
            user.sendMessage("  ");
            user.sendMessage("     &7You earned:");
            for(Map.Entry<Currency, Integer> earnedStatisticEntry : rewardSummary.getEarnedStatistics().entrySet()) {
                Currency currency = earnedStatisticEntry.getKey();
                int amount = earnedStatisticEntry.getValue();
                String displayAmount = currency.getDisplayAmount(amount);
                user.sendMessage("         • " + displayAmount);
            }
            user.sendMessage(" ");
            user.sendMessage(ChatColor.GREEN + repeatLine);
        });
    }
}