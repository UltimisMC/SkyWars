package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.Map;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import com.ultimismc.skywars.game.GameManager;
import com.ultimismc.skywars.game.GameServerInitializer;
import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.chest.GameChestRegistry;
import com.ultimismc.skywars.game.combat.SkyWarsCombatAdapter;
import com.ultimismc.skywars.game.combat.SkyWarsCombatManager;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.events.SkyWarsEventHandler;
import com.ultimismc.skywars.game.handler.runnable.GamePreparer;
import com.ultimismc.skywars.game.handler.runnable.GameRunnable;
import com.ultimismc.skywars.game.handler.scoreboard.AccompaniedGameScoreboard;
import com.ultimismc.skywars.game.handler.scoreboard.GameScoreboard;
import com.ultimismc.skywars.game.handler.scoreboard.SoloGameScoreboard;
import com.ultimismc.skywars.game.handler.setup.GameSetupHandler;
import com.ultimismc.skywars.game.island.Island;
import com.ultimismc.skywars.game.island.IslandHandler;
import com.ultimismc.skywars.game.menubar.GameSpectatorBarMenu;
import com.ultimismc.skywars.game.menubar.UserWaitingBarMenu;
import com.ultimismc.skywars.game.mode.InsaneGame;
import com.ultimismc.skywars.game.mode.NormalGame;
import com.ultimismc.skywars.game.user.UserGameSession;
import com.ultimismc.skywars.game.user.UserSessionHandler;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.scoreboard.ScoreboardManager;

import java.util.Collection;
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
    private final MenuManager menuManager;

    private GameServer gameServer;
    private GameServerInitializer gameServerInitializer;
    private Game game;
    private GameScoreboard gameScoreboard;
    private World gameWorld;

    @Setter private long prepareCountdownLeft;
    @Setter private long gameTime;

    private UserSessionHandler userSessionHandler;
    private GameSetupHandler gameSetupHandler;
    private SkyWarsEventHandler skyWarsEventHandler;
    private ChestHandler chestHandler;
    private IslandHandler islandHandler;
    private CosmeticManager cosmeticManager;
    private SkyWarsCombatManager combatManager;

    private BukkitTask gamePreparer, gameTask;

    public GameHandler(SkyWarsPlugin plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
        menuManager = plugin.getMenuManager();
        userManager = plugin.getUserManager();
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        FeatureHandler featureHandler = plugin.getFeatureHandler();
        cosmeticManager = featureHandler.getCosmeticManager();

        skyWarsEventHandler = new SkyWarsEventHandler(plugin, this);
        islandHandler = new IslandHandler(this);
        chestHandler = new ChestHandler(this);

        gameServerInitializer = new GameServerInitializer(plugin, this);

        gameServerInitializer.initializeServer();
        gameServer = gameServerInitializer.getGameServer();
        gameWorld = gameServerInitializer.getGameWorld();

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
        GameChestRegistry chestRegistry = game.getChestRegistry();
        chestRegistry.buildItems();

        log(plugin, "Loaded " + chestRegistry.getSize() + " chest items for " + gameType.getName() + " mode.");
        userSessionHandler = new UserSessionHandler(gameServer);

        ScoreboardManager scoreboardManager = gameManager.getScoreboardManager();
        gameScoreboard = new SoloGameScoreboard(scoreboardManager, this);
        if(!gameServer.isSoloGame()) {
            gameScoreboard = new AccompaniedGameScoreboard(scoreboardManager, this);
        }
        gameSetupHandler = new GameSetupHandler(this);

        SkyWarsCombatAdapter combatAdapter = new SkyWarsCombatAdapter(this, featureHandler);
        combatManager = new SkyWarsCombatManager(plugin, combatAdapter);
        combatManager.startCombatManager();

        gameTask = plugin.getServer().getScheduler().runTaskTimer(plugin, new GameRunnable(this), 0L, 20L);
        plugin.log("Game Server for SkyWars " + gameServer.getName() + " has started.");
    }

    public void shutdown() {
        chestHandler.shutdown();
        islandHandler.shutdown();
        gameServerInitializer.finalizeServer();
    }

    public void prepareUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.JOIN_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName),
                new Replacement("current-players", getOnlinePlayers()),
                new Replacement("maximum-players", gameServer.getMaximumPlayers()));

        Player player = user.getPlayer();
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        PluginUtility.sendTitle(player, 20, 40, 20, "&eSkyWars", gameServer.getGameDisplayName() + " mode");

        UserGameSession userGameSession = userSessionHandler.addUser(user);
        game.prepareUser(user);

        menuManager.applyDesign(new UserWaitingBarMenu(this, user), true, false);
        islandHandler.handleCageJoin(userGameSession);
        if(hasMinimumPlayers()) {
            startPreparer();
        }
        updateScoreboard();
    }

    public void quitUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.QUIT_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName));

        UserGameSession userGameSession = userSessionHandler.removeSession(user);
        gameManager.removeScoreboard(user.getUuid());
        game.quitUser(user);

        islandHandler.handleCageQuit(userGameSession);
        if(game.isStarting() && !hasMinimumPlayers()) {
            broadcastMessage("&cNot enough players!");
            cancelPreparer();
        }

        updateScoreboard();
    }

    public void startPreparer(int seconds) {
        game.setGameState(GameState.STARTING);
        gamePreparer = plugin.getServer().getScheduler().runTaskTimer(plugin, new GamePreparer(this, seconds), 0, 20L);

    }

    public void startPreparer() {
        startPreparer(15);
    }

    public void cancelPreparer() {
        game.setGameState(GameState.WAITING);
        gamePreparer.cancel();
    }

    public void startGame() {
        islandHandler.removeAllCages();
        gamePreparer.cancel();
        game.setGameState(GameState.STARTED);
        game.startGame();
        skyWarsEventHandler.startNextEvent();
        chestHandler.refillAllChests();

        broadcastFunction(user -> PluginUtility.playSound(user.getPlayer(), Sound.FIREWORK_BLAST));
        String repeatLine = StringUtils.repeat("▬", 70);
        broadcastFunction(user -> {
            Player player = user.getPlayer();
            player.closeInventory();
            menuManager.clearInventory(user);

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
    }

    public void endGame() {
        game.setGameState(GameState.ENDED);
        game.endGame();

    }

    public void terminateUser(User user) {
        MessageConfigKeys.DEATH_MESSAGE.sendMessage(user.getPlayer());
        UserGameSession userGameSession = getSession(user);
        addSpectator(userGameSession);
    }

    public void addSpectator(UserGameSession userGameSession) {
        User user = userGameSession.getUser();
        Player player = user.getPlayer();

        userGameSession.setSpectator(true);

        Island userIsland = userGameSession.getCurrentIsland();
        user.teleport(userIsland.getCageLocation());

        player.setAllowFlight(true);
        player.setFlying(true);

        // Make player invisible and that he can see
        // spectators too
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
        menuManager.applyDesign(new GameSpectatorBarMenu(this, user), true, false);
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

    public UserGameSession getSession(Player player) {
        return userSessionHandler.getSession(player);
    }

    public Collection<UserGameSession> getUserSessions() {
        return userSessionHandler.getUserSessions();
    }

    public int getMaximumPlayers() {
        return gameServer.getMaximumPlayers();
    }

    public Map getServerMap() {
        return gameServer.getMap();
    }

    public String getServerId() {
        return gameServer.getServerId();
    }

    public String getServerName() {
        return gameServer.getName();
    }

    public TeamType getTeamType() {
        return gameServer.getTeamType();
    }

    public int getRegisteredChests() {
        return chestHandler.getSize();
    }

    public int getRegisteredIslands() {
        return islandHandler.getSize();
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

    public boolean hasTimePassed(int seconds) {
        long millis = (seconds * 1000L);
        return gameTime >= millis;
    }

    public boolean hasStarted() {
        return game.hasStarted();
    }

    public boolean isOpen() {
        return !game.hasStarted() && getOnlinePlayers() < getMaximumPlayers();
    }

    public void openInventory(Player player, InventoryUI inventoryUI) {
        menuManager.openInventory(player, inventoryUI);
    }

    public FeatureHandler getFeatureHandler() {
        return plugin.getFeatureHandler();
    }

    public void log(String message) {
        log(plugin, message);
    }
}
