package com.ultimismc.skywars.game.handler.scoreboard;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import xyz.directplan.directlib.config.replacement.Replacement;

import java.util.List;

/**
 * @author DirectPlan
 */
public class SoloGameScoreboard extends GameScoreboard {

    public SoloGameScoreboard(GameHandler gameHandler) {
        super(gameHandler);
    }

    @Override
    public GameScoreboardInfo getGameScoreboard(User user) {
        UserGameSession userGameSession = gameHandler.getSession(user);

        int kills = userGameSession.getKills();

        String serverId = gameServer.getServerId();
        int playersLeft = gameHandler.getPlayersLeft();;
        String mapName = gameServer.getMapName();
        String modeName = gameServer.getGameName();

        String displayName = MessageConfigKeys.SKYWARS_GAME_SCOREBOARD_DISPLAYNAME.getStringValue();

        String nextEvent = gameHandler.getNextEventDisplayFormat();

        List<String> scoreboardLines = MessageConfigKeys.SKYWARS_GAME_STARTED_SCOREBOARD_LINES
                .getStringList(new Replacement("next-event", nextEvent),
                        new Replacement("server-id", serverId),
                        new Replacement("players-left", playersLeft),
                        new Replacement("kills", kills),
                        new Replacement("map", mapName),
                        new Replacement("mode", modeName));

        return new GameScoreboardInfo(displayName, scoreboardLines);
    }
}
