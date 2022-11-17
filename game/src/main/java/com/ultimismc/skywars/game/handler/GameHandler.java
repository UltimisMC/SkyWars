package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import com.ultimismc.skywars.game.GameManager;
import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.events.SkyWarsEventHandler;
import com.ultimismc.skywars.game.handler.runnable.GamePreparer;
import com.ultimismc.skywars.game.handler.scoreboard.AccompaniedGameScoreboard;
import com.ultimismc.skywars.game.handler.scoreboard.GameScoreboard;
import com.ultimismc.skywars.game.handler.scoreboard.SoloGameScoreboard;
import com.ultimismc.skywars.game.handler.setup.GameSetupHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import com.ultimismc.skywars.game.user.UserSessionHandler;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitTask;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.scoreboard.ScoreboardManager;

import java.util.function.Consumer;

/**
 * @author DirectPlan
 */
@Getter
public class GameHandler implements FeatureInitializer {

    private final String name = "Game: Game Handler";

    private final SkyWarsPlugin plugin;
    private final UserManager userManager;
    private final GameManager gameManager;
    private final ScoreboardManager scoreboardManager;

    private Game game;
    private final GameServer gameServer;
    private GameScoreboard gameScoreboard;

    private final long preparerCountdown = (1000L * 15);
    @Setter private long prepareCountdownLeft;
    @Setter private long gameTime;

    private final UserSessionHandler userSessionHandler;
    private GameSetupHandler gameSetupHandler;
    private SkyWarsEventHandler skyWarsEventHandler;
    private ChestHandler chestHandler;

    private BukkitTask gamePreparer;

    public GameHandler(SkyWarsPlugin plugin, GameManager gameManager, GameServer gameServer) {
        this.plugin = plugin;
        this.gameManager = gameManager;
        this.gameServer = gameServer;

        prepareCountdownLeft = preparerCountdown;
        userManager = plugin.getUserManager();
        userSessionHandler = new UserSessionHandler(gameServer);
        scoreboardManager = new ScoreboardManager(plugin, "Ultimis SkyWars Scoreboard");
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

        gameScoreboard = new SoloGameScoreboard(this);
        if(!gameServer.isSoloGame()) {
            gameScoreboard = new AccompaniedGameScoreboard(this);
        }
        gameSetupHandler = new GameSetupHandler(gameManager);
        skyWarsEventHandler = new SkyWarsEventHandler(plugin, this);
        chestHandler = new ChestHandler(this);
        plugin.log("Game Server for SkyWars " + gameServer.getName() + " has started.");
    }

    public void prepareUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.JOIN_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName),
                new Replacement("current-players", getOnlinePlayers()),
                new Replacement("maximum-players", gameServer.getMaximumPlayers()));

        userSessionHandler.addUser(user);
        game.prepareUser(user);

        if(hasMinimumPlayers()) {
            startPreparer();
        }
        updateScoreboard();
    }

    public void quitUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.QUIT_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName));

        scoreboardManager.removeScoreboard(user.getUuid());
        game.quitUser(user);

        if(!game.hasStarted() && !hasMinimumPlayers()) {
            broadcastMessage("&cNot enough players!");
            cancelPreparer();
        }

        updateScoreboard();
    }

    public void startPreparer() {
        game.setGameState(GameState.STARTING);
        gamePreparer = plugin.getServer().getScheduler().runTaskTimer(plugin, new GamePreparer(this), 0, 20L);

    }
    public void cancelPreparer() {
        game.setGameState(GameState.WAITING);
        gamePreparer.cancel();
    }

    public void startGame() {
        gamePreparer.cancel();
        game.setGameState(GameState.STARTED);
        skyWarsEventHandler.startNextEvent();

        broadcastFunction(user -> PluginUtility.playSound(user.getPlayer(), Sound.FIREWORK_BLAST));
        String repeatLine = StringUtils.repeat("▬", 70);
        broadcastFunction(user -> {
            user.sendMessage(ChatColor.GREEN + repeatLine);
            user.sendMessage("                             &f&lSkyWars");
            user.sendMessage(" ");
            user.sendMessage("             &e&lGather resources and equipment on your");
            user.sendMessage("       &e&lisland in order to eliminate every other player.");
            user.sendMessage("             &e&lGo to the center island for special chests");
            user.sendMessage("                           &e&lwith special items!");
            user.sendMessage(" ");
            user.sendMessage(ChatColor.GREEN + repeatLine);
        });
        // All cages gets opened
        broadcastMessage("&eCages opened! &cFIGHT!");
        if(gameServer.isSoloGame()) {
            broadcastMessage("&c&lTeaming is not allowed on Solo Mode!");
        }
        updateScoreboard();
    }

    public void endGame() {
        game.setGameState(GameState.ENDED);

    }

    public void terminateUser(User user) {
        UserGameSession userGameSession = getSession(user);

    }

    public void addSpectator(User user) {
        game.addSpectator(user);

    }

    public void removeSpectator(User user) {
        game.removeSpectator(user);

    }

    public void updateScoreboard() {
        broadcastFunction(user -> gameScoreboard.updateScoreboard(user));
    }

    public void broadcastMessage(String message) {
        broadcastFunction(user -> user.sendMessage(message));
    }

    public void broadcastTitle(String message) {
        broadcastFunction(user -> user.sendMessage("Title: " + message));
    }

    public void broadcastFunction(Consumer<User> consumer) {
        for(User user : userManager.getUsers().values()) {
            consumer.accept(user);
        }
    }

    public String getNextEventDisplayFormat() {
        return skyWarsEventHandler.getNextEventDisplayFormat();
    }

    public UserGameSession getSession(User user) {
        return userSessionHandler.getSession(user);
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

    public boolean hasMinimumPlayers() {
        return game.getPlayers().size() >= game.getMinimumPlayers();
    }

    public int getPlayersLeft() {
        return game.getPlayers().size();
    }

    public boolean hasStarted() {
        return game.hasStarted();
    }

    public boolean isOpen() {
        return !game.hasStarted() && getOnlinePlayers() < getMaximumPlayers();
    }
}
