package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GameEndStatsPhase extends GameEndPhase {

    public GameEndStatsPhase(GameHandler gameHandler) {
        super(gameHandler, 0);
    }
    @Override
    public void executePhase(UserGameSession winner, Collection<UserGameSession> players) {

        List<UserGameSession> topKillers = players.stream().sorted((o1, o2) -> Integer.compare(o2.getKills(), o1.getKills()))
                .collect(Collectors.toList());

        UserGameSession firstKiller = topKillers.get(0);
        UserGameSession secondKiller = null;
        UserGameSession thirdKiller = null;
        if(topKillers.size() >= 2) {
            secondKiller = topKillers.get(1);
        }
        if(topKillers.size() >= 3) {
            thirdKiller = topKillers.get(2);
        }

        UserGameSession finalSecondKiller = secondKiller;
        UserGameSession finalThirdKiller = thirdKiller;
        String repeatLine = StringUtils.repeat("▬", 70);
        gameHandler.broadcastFunction(userGameSession -> {
            User user = userGameSession.getUser();
            user.sendMessage(ChatColor.GREEN + repeatLine);
            user.sendMessage("                             &f&lSkyWars");
            user.sendMessage(" ");
            user.sendMessage("                       &eWinner &7- " + winner.getDisplayName());
            user.sendMessage(" ");
            user.sendMessage("                &e&l1st Killer &7- " + getKillerDisplayInfo(firstKiller));
            user.sendMessage("                   &6&l2nd Killer &7- " + getKillerDisplayInfo(finalSecondKiller));
            user.sendMessage("                   &c&l3rd Killer &7- " + getKillerDisplayInfo(finalThirdKiller));
            user.sendMessage(" ");
            user.sendMessage(ChatColor.GREEN + repeatLine);
        });
        MessageConfigKeys.WON_MESSAGE.sendMessage(winner.getPlayer());
    }

    private String getKillerDisplayInfo(UserGameSession userGameSession) {
        if(userGameSession == null) {
            return "&7N/A - 0";
        }
        String displayName = userGameSession.getDisplayName();
        int kills = userGameSession.getKills();
        return displayName + " &7- " + kills;
    }
}
