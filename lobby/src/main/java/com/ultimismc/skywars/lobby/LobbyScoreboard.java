package com.ultimismc.skywars.lobby;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.lobby.config.MessageConfigKeys;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.StringUtil;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.scoreboard.ScoreboardManager;

import java.util.List;
import java.util.UUID;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LobbyScoreboard {

    private final ScoreboardManager scoreboardManager;

    public void updateGameScoreboard(User user) {
        Player player = user.getPlayer();

        String gameDisplayName = MessageConfigKeys.SKYWARS_LOBBY_SCOREBOARD_DISPLAYNAME.getStringValue();
        UserStatistics userStatistics = user.getStatistics();
        String levelDisplayName = user.getLevelDisplayName();

        int soloKills = userStatistics.getSoloKills();
        int soloWins = userStatistics.getSoloWins();
        int doublesKills = userStatistics.getDoublesKills();
        int doublesWins = userStatistics.getDoublesWins();
        int coins = userStatistics.getCoins();
        int souls = userStatistics.getSouls();
        int maximumSouls = userStatistics.getMaximumSouls();

        List<String> scoreboardLines = MessageConfigKeys.SKYWARS_LOBBY_SCOREBOARD_LINES
                .getStringList(new Replacement("level", levelDisplayName),
                        new Replacement("solo-kills", rNumber(soloKills)),
                        new Replacement("solo-wins", rNumber(soloWins)),
                        new Replacement("doubles-kills", rNumber(doublesKills)),
                        new Replacement("doubles-wins", rNumber(doublesWins)),
                        new Replacement("coins", Currency.COIN_CURRENCY.getDisplayAmount(coins)),
                        new Replacement("souls", souls),
                        new Replacement("maximum-souls", maximumSouls));
        scoreboardLines = PlaceholderAPI.setPlaceholders(player, scoreboardLines);
        scoreboardManager.sendScoreboard(player, gameDisplayName, scoreboardLines);
    }

    public void removeScoreboard(User user) {
        UUID uuid = user.getUuid();
        scoreboardManager.removeScoreboard(uuid);
    }

    private String rNumber(int num) {
        return StringUtil.getReadableNumber(num);
    }
}
