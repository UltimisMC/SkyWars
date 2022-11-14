package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import com.ultimismc.skywars.game.user.UserSessionHandler;
import lombok.Getter;

/**
 * @author DirectPlan
 */
public class GameHandler implements FeatureInitializer {

    @Getter private final String name = "Game: Game Handler";

    private final UserManager userManager;

    private Game game;
    private final GameServer gameServer;

    private final UserSessionHandler userSessionHandler;

    public GameHandler(SkyWarsPlugin plugin, GameServer gameServer) {
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
        plugin.log("Game Server for SkyWars " + gameServer.getName() + " has started.");
    }

    public void prepareUser(User user) {
        userSessionHandler.addUser(user);
        game.prepareUser(user);
    }

    public void quitUser(User user) {
        game.quitUser(user);
    }

    public int getMaximumPlayers() {
        return gameServer.getMaximumPlayers();
    }

    public String getServerId() {
        return gameServer.getServerId();
    }

    public int getOnlinePlayers() {
        return userManager.getUsers().size();
    }
}
