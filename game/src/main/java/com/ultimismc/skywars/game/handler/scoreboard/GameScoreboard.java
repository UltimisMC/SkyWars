package com.ultimismc.skywars.game.handler.scoreboard;

import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.handler.Game;
import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.Data;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.scoreboard.ScoreboardManager;

import java.util.List;

/**
 * @author DirectPlan
 */
public abstract class GameScoreboard {

    private final ScoreboardManager scoreboardManager;

    protected final GameHandler gameHandler;
    protected final GameServer gameServer;
    protected final Game game;

    public GameScoreboard(ScoreboardManager scoreboardManager, GameHandler gameHandler) {
        this.scoreboardManager = scoreboardManager;
        this.gameHandler = gameHandler;
        gameServer = gameHandler.getGameServer();
        game = gameHandler.getGame();
    }

    public abstract GameScoreboardInfo getGameScoreboard(User user);

    public void updateScoreboard(User user) {
        GameServer gameServer = gameHandler.getGameServer();
        Game game = gameHandler.getGame();
        if(game.hasStarted()) {
            GameScoreboardInfo scoreboardInfo = getGameScoreboard(user);
            updateScoreboard(user, scoreboardInfo);
            return;
        }

        String serverId = gameServer.getServerId();
        int currentPlayers = gameHandler.getPlayersLeftSize();
        int maximumPlayers = gameServer.getMaximumPlayers();
        String gameStatus = MessageConfigKeys.SKYWARS_GAME_WAITING_STATUS_SCOREBOARD.getStringValue();
        if(game.isStarting()) {
            long prepareCountdownLeft = gameHandler.getPrepareCountdownLeft();
            int countdownLeftSeconds = (int) prepareCountdownLeft / 1000;
            gameStatus = MessageConfigKeys.SKYWARS_GAME_STARTING_STATUS_SCOREBOARD.
                    getStringValue(new Replacement("time", countdownLeftSeconds + "s"));
        }
        String mapName = gameServer.getMapName();
        String modeDisplayName = gameServer.getGameDisplayName();

        String displayName = MessageConfigKeys.SKYWARS_GAME_SCOREBOARD_DISPLAYNAME.getStringValue();

        List<String> scoreboardLines = MessageConfigKeys.SKYWARS_GAME_WAITING_SCOREBOARD_LINES
                .getStringList(new Replacement("server-id", serverId),
                        new Replacement("current-players", currentPlayers),
                        new Replacement("maximum-players", maximumPlayers),
                        new Replacement("game-status", gameStatus),
                        new Replacement("map", mapName),
                        new Replacement("mode", modeDisplayName));

        updateScoreboard(user, new GameScoreboardInfo(displayName, scoreboardLines));
    }

    private void updateScoreboard(User user, GameScoreboardInfo scoreboardInfo) {
        Player player = user.getPlayer();

        String displayName = scoreboardInfo.getDisplayName();
        List<String> scoreboardLines = scoreboardInfo.getScoreboardLines();

        scoreboardLines = PlaceholderAPI.setPlaceholders(player, scoreboardLines);
        scoreboardManager.sendScoreboard(user.getPlayer(), displayName, scoreboardLines);
    }

    @Data
    static class GameScoreboardInfo {

        private final String displayName;
        private final List<String> scoreboardLines;
    }
}
