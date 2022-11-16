package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import com.ultimismc.skywars.game.GameManager;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.handler.setup.GameSetupHandler;
import com.ultimismc.skywars.game.user.UserSessionHandler;
import lombok.Getter;
import xyz.directplan.directlib.config.replacement.Replacement;

/**
 * @author DirectPlan
 */
@Getter
public class GameHandler implements FeatureInitializer {

    private final String name = "Game: Game Handler";

    private final UserManager userManager;
    private final GameManager gameManager;

    private Game game;
    private final GameServer gameServer;

    private final UserSessionHandler userSessionHandler;
    private GameSetupHandler gameSetupHandler;

    public GameHandler(SkyWarsPlugin plugin, GameManager gameManager, GameServer gameServer) {
        this.gameManager = gameManager;
        this.gameServer = gameServer;

        userManager = plugin.getUserManager();
        userSessionHandler = new UserSessionHandler(gameServer);
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        GameType gameType = gameServer.getGameType();
        switch (gameType) {
            case NORMAL: {
                game = new NormalGame(this);
                break;
            }
            case INSANE: {
                game = new InsaneGame(this);
            }
            break;
            default:
                throw new IllegalStateException("Unexpected SkyWars Mode: " + gameType);
        }

        gameSetupHandler = new GameSetupHandler(gameManager);
        plugin.log("Game Server for SkyWars " + gameServer.getName() + " has started.");
    }

    public void prepareUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.JOIN_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName),
                new Replacement("current-players", getOnlinePlayers()),
                new Replacement("maximum-players", gameServer.getMaximumPlayers()));

        userSessionHandler.addUser(user);
        game.prepareUser(user);
    }

    public void quitUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.QUIT_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName));

        game.quitUser(user);
    }

    public int getMaximumPlayers() {
        return gameServer.getMaximumPlayers();
    }

    public String getServerId() {
        return gameServer.getServerId();
    }

    public String getServerName() {
        return gameServer.getName();
    }

    public int getOnlinePlayers() {
        return userManager.getUsers().size();
    }
}
