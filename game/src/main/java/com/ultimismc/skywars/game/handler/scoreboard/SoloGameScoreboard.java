package com.ultimismc.skywars.game.handler.scoreboard;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.scoreboard.ScoreboardManager;

import java.util.List;

/**
 * @author DirectPlan
 */
public class SoloGameScoreboard extends GameScoreboard {

    public SoloGameScoreboard(ScoreboardManager scoreboardManager, GameHandler gameHandler) {
        super(scoreboardManager, gameHandler);
    }

    @Override
    public GameScoreboardInfo getGameScoreboard(User user) {
        UserGameSession userGameSession = gameHandler.getSession(user);

        int kills = userGameSession.getKills();

        String serverId = gameConfig.getServerId();
        int playersLeft = gameHandler.getPlayersLeftSize();
        String mapName = gameConfig.getMapName();
        String modeDisplayName = gameConfig.getGameDisplayName();

        String displayName = MessageConfigKeys.SKYWARS_GAME_SCOREBOARD_DISPLAYNAME.getStringValue();

        String nextEvent = gameHandler.getNextEventDisplayFormat();

        List<String> scoreboardLines = MessageConfigKeys.SKYWARS_GAME_STARTED_SCOREBOARD_LINES
                .getStringList(new Replacement("next-event", nextEvent),
                        new Replacement("server-id", serverId),
                        new Replacement("players-left", playersLeft),
                        new Replacement("kills", kills),
                        new Replacement("map", mapName),
                        new Replacement("mode", modeDisplayName));

        return new GameScoreboardInfo(displayName, scoreboardLines);
    }
}
