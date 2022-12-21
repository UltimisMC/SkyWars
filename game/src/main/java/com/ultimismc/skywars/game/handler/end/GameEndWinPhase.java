package com.ultimismc.skywars.game.handler.end;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.victorydances.VictoryDanceHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.handler.team.GameTeam;
import com.ultimismc.skywars.game.user.UserGameSession;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import xyz.directplan.directlib.PluginUtility;

import java.util.*;
import java.util.stream.Collectors;

public class GameEndWinPhase extends GameEndPhase {

    public GameEndWinPhase(GameHandler gameHandler) {
        super(gameHandler, 0);
    }
    @Override
    public void executePhase(GameTeam winnerTeam, Collection<GameTeam> teams) {

        List<GameTeam> topTeams = teams.stream().sorted((o1, o2) -> Integer.compare(o2.getCombinedKills(), o1.getCombinedKills()))
                .collect(Collectors.toList());

        GameTeam firstTeam = topTeams.get(0);
        GameTeam secondTeam = null;
        GameTeam thirdTeam = null;
        if(topTeams.size() >= 2) {
            secondTeam = topTeams.get(1);
        }
        if(topTeams.size() >= 3) {
            thirdTeam = topTeams.get(2);
        }

        GameTeam finalSecondTeam = secondTeam;
        GameTeam finalThirdTeam = thirdTeam;
        String repeatLine = StringUtils.repeat("▬", 70);
        gameHandler.broadcastFunction(userGameSession -> {
            User user = userGameSession.getUser();
            user.sendMessage(ChatColor.GREEN + repeatLine);
            user.sendMessage("                             &f&lSkyWars");
            user.sendMessage(" ");
            user.sendMessage("                       &eWinner &7- " + getPlayerDisplayNames(winnerTeam));
            user.sendMessage(" ");
            user.sendMessage("                &e&l1st Killer &7- " + getKillerDisplayInfo(firstTeam));
            user.sendMessage("                   &6&l2nd Killer &7- " + getKillerDisplayInfo(finalSecondTeam));
            user.sendMessage("                   &c&l3rd Killer &7- " + getKillerDisplayInfo(finalThirdTeam));
            user.sendMessage(" ");
            user.sendMessage(ChatColor.GREEN + repeatLine);
        });

        for(UserGameSession winner : winnerTeam) {
            MessageConfigKeys.WON_MESSAGE.sendMessage(winner.getPlayer());

            winner.increaseWin();

            int expReward = (winner.getKills() * 3);
            if(expReward < 10) {
                expReward = 10;
            }
            CosmeticManager cosmeticManager = gameHandler.getCosmeticManager();
            VictoryDanceHandler victoryDanceHandler = cosmeticManager.getVictoryDanceHandler();
            victoryDanceHandler.playVictoryDance(winner.getUser());

            winner.addCurrencyStat(com.ultimismc.skywars.core.game.currency.Currency.EXP_CURRENCY, expReward, "Win", true);
            winner.addCurrencyStat(Currency.COIN_CURRENCY, 1400, "Game End", true);
            PluginUtility.sendTitle(winner.getPlayer(), 0, 60, 0, "&6&lVICTORY!", "&7You were the last man standing!");
        }
        for(UserGameSession everyone : gameHandler.getUserSessions()) {
            if(everyone.isOnline() && !winnerTeam.isMember(everyone)) {
                PluginUtility.sendTitle(everyone.getPlayer(), 0, 60, 0, "&c&lGAME END", "&7You weren't victorious this time");
            }
        }
    }

    private String getKillerDisplayInfo(GameTeam gameTeam) {
        if(gameTeam == null) {
            return "&7N/A - 0";
        }

        Collection<UserGameSession> teamPlayers = gameTeam.getPlayers().values();

        List<String> playerDisplayNames = getPlayerDisplayNames(teamPlayers);

        String displayName = String.join("&7, ", playerDisplayNames);
        int kills = gameTeam.getCombinedKills();
        return displayName + " &7- " + kills;
    }

    private List<String> getPlayerDisplayNames(GameTeam gameTeam) {
        Map<UUID, UserGameSession> teamPlayers = gameTeam.getPlayers();
        return getPlayerDisplayNames(teamPlayers.values());
    }

    private List<String> getPlayerDisplayNames(Collection<UserGameSession> teamPlayers) {

        List<String> playerDisplayNames = new ArrayList<>();
        for(UserGameSession player : teamPlayers) {
            playerDisplayNames.add(player.getDisplayName());
        }
        return playerDisplayNames;
    }
}
