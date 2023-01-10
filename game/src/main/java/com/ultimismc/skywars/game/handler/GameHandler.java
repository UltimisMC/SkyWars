package com.ultimismc.skywars.game.handler;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameState;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.kits.KitManager;
import com.ultimismc.skywars.core.game.menu.GameMenuHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import com.ultimismc.skywars.game.GameManager;
import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.chest.GameChestRegistry;
import com.ultimismc.skywars.game.combat.SkyWarsCombatAdapter;
import com.ultimismc.skywars.game.combat.SkyWarsCombatManager;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.events.SkyWarsEventHandler;
import com.ultimismc.skywars.game.handler.runnable.GameEndRunnable;
import com.ultimismc.skywars.game.handler.runnable.GamePreparer;
import com.ultimismc.skywars.game.handler.runnable.GameRunnable;
import com.ultimismc.skywars.game.handler.scoreboard.AccompaniedGameScoreboard;
import com.ultimismc.skywars.game.handler.scoreboard.GameScoreboard;
import com.ultimismc.skywars.game.handler.scoreboard.SoloGameScoreboard;
import com.ultimismc.skywars.game.handler.setup.GameSetupHandler;
import com.ultimismc.skywars.game.handler.team.GameTeam;
import com.ultimismc.skywars.game.handler.team.GameTeamHandler;
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
import java.util.LinkedList;
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
    private final GameConfig gameConfig;
    private final GameMenuHandler gameMenuHandler;

    private Game game;
    private GameScoreboard gameScoreboard;

    @Setter private long prepareCountdownLeft;
    @Setter private long gameTime;

    private UserSessionHandler userSessionHandler;
    private GameSetupHandler gameSetupHandler;
    private SkyWarsEventHandler skyWarsEventHandler;
    private ChestHandler chestHandler;
    private IslandHandler islandHandler;
    private CosmeticManager cosmeticManager;
    private SkyWarsCombatManager combatManager;
    private GameTeamHandler teamHandler;
    private KitManager kitManager;

    private BukkitTask gamePreparer, gameTask;

    public GameHandler(SkyWarsPlugin plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;

        gameConfig = plugin.getGameConfig();
        menuManager = plugin.getMenuManager();
        userManager = plugin.getUserManager();
        gameMenuHandler = plugin.getGameMenuHandler();
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        FeatureHandler featureHandler = plugin.getFeatureHandler();
        cosmeticManager = featureHandler.getCosmeticManager();
        kitManager = featureHandler.getKitManager();

        skyWarsEventHandler = new SkyWarsEventHandler(plugin, this);
        islandHandler = new IslandHandler(this);
        chestHandler = new ChestHandler(this);
        featureHandler.initializeFeature(chestHandler);
        featureHandler.initializeFeature(islandHandler);

        GameType gameType = gameConfig.getGameType();
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
        userSessionHandler = new UserSessionHandler(gameConfig);

        ScoreboardManager scoreboardManager = gameManager.getScoreboardManager();
        gameScoreboard = new SoloGameScoreboard(scoreboardManager, this);
        if(!gameConfig.isSoloGame()) {
            gameScoreboard = new AccompaniedGameScoreboard(scoreboardManager, this);
        }
        gameSetupHandler = new GameSetupHandler(this);

        SkyWarsCombatAdapter combatAdapter = new SkyWarsCombatAdapter(plugin, this, featureHandler);
        combatManager = new SkyWarsCombatManager(plugin, this, combatAdapter);
        combatManager.startCombatManager();
        teamHandler = new GameTeamHandler(this);

        gameTask = plugin.getServer().getScheduler().runTaskTimer(plugin, new GameRunnable(this), 0L, 20L);
        plugin.log("Game Server for SkyWars " + gameConfig.getName() + " has started.");
    }

    public void prepareUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.JOIN_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName),
                new Replacement("current-players", getOnlinePlayers()),
                new Replacement("maximum-players", gameConfig.getMaximumPlayers()));

        Player player = user.getPlayer();
        player.getInventory().clear();
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        PluginUtility.sendTitle(player, 20, 40, 20, "&eSkyWars", gameConfig.getGameDisplayName() + " mode");

        UserGameSession userGameSession = userSessionHandler.addUser(user);
        teamHandler.handleTeamJoin(userGameSession);
        game.prepareUser(userGameSession);
        islandHandler.handleCageJoin(userGameSession);

        menuManager.applyDesign(new UserWaitingBarMenu(this, user), true, false);
        if(hasMinimumPlayers()) {
            startPreparer();
        }
        updateScoreboard();
    }

    public void quitUser(User user) {
        String userDisplayName = user.getDisplayName();
        MessageConfigKeys.QUIT_MESSAGE.broadcastMessage(new Replacement("player", userDisplayName));

        UserGameSession userGameSession;
        if(hasStarted()) {
            userGameSession = getSession(user);
        }else {
            userGameSession = userSessionHandler.removeSession(user);
        }

        gameManager.removeScoreboard(user.getUuid());
        game.quitUser(userGameSession);

        if(!game.hasStarted()) {
            islandHandler.handleCageQuit(userGameSession);
            teamHandler.handleTeamQuit(userGameSession);
        }
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
        broadcastFunction(userGameSession -> {
            User user = userGameSession.getUser();
            Player player = user.getPlayer();
            player.closeInventory();
            menuManager.clearInventory(user);
            GameType gameType = getGameType();
            kitManager.giveKit(user, gameType);
            teamHandler.setupTeamTag(userGameSession);
            PluginUtility.sendActionBar(user.getPlayer(), "");

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
        if(isSoloGame()) {
            broadcastMessage("&c&lTeaming is not allowed on Solo Mode!");
        }
    }

    public void endGame() {
        game.setGameState(GameState.ENDED);
        game.endGame();

        GameTeam winnerTeam = game.getLastTeamAlive();
        plugin.getServer().getScheduler().runTaskTimer(plugin, new GameEndRunnable(plugin, this, winnerTeam, game.getGameTeams()), 0, 20L);
    }

    public void terminateUser(UserGameSession userGameSession) {
        MessageConfigKeys.DEATH_MESSAGE.sendMessage(userGameSession.getPlayer());

        game.terminatePlayer(userGameSession); // Currently, useless as fuck

        userGameSession.addCurrencyStat(Currency.COIN_CURRENCY, 1400, "Game End", true);

        if(game.getTeamsLeft() <= 1 && !hasEnded()) {
            endGame();
        }
        addSpectator(userGameSession);
    }

    public void addSpectator(UserGameSession userGameSession) {
        game.addSpectator(userGameSession);
        User user = userGameSession.getUser();
        Player player = user.getPlayer();

        userGameSession.setSpectator(true);

        userGameSession.teleportToIsland();

        PluginUtility.removeStuckArrowsFromPlayer(player);
        player.setAllowFlight(true);
        player.setFlying(true);


        // spectators too
        menuManager.applyDesign(new GameSpectatorBarMenu(this, user), true, false);

        // Make player invisible and that he can see
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
        for(UserGameSession session : getUserSessions()) {
            Player otherPlayer = session.getPlayer();
            if(session.isSpectator()) {
                player.showPlayer(otherPlayer);
                continue;
            }
            otherPlayer.hidePlayer(player);
        }
        // Set a gray name tag
        teamHandler.setupTeamTag(userGameSession);
    }

    public void updateScoreboard() {
        broadcastFunction(userGameSession -> gameScoreboard.updateScoreboard(userGameSession.getUser()));
    }

    public void broadcastMessage(String message) {
        broadcastFunction(user -> user.sendMessage(message));
    }

    public void broadcastFunction(Consumer<UserGameSession> consumer) {
        for(UserGameSession user : getUserSessions()) {
            if(!user.isOnline()) continue;
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
        return gameConfig.getMaximumPlayers();
    }

    public String getServerId() {
        return gameConfig.getServerId();
    }

    public String getServerName() {
        return gameConfig.getName();
    }

    public TeamType getTeamType() {
        return gameConfig.getTeamType();
    }

    public GameType getGameType() {
        return gameConfig.getGameType();
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
        return getOnlinePlayers() >= game.getMinimumPlayers();
    }

    public int getPlayersLeftSize() {
        return getPlayersLeft().size();
    }

    public void setGameState(GameState gameState) {
        game.setGameState(gameState);
    }

    public LinkedList<UserGameSession> getPlayersLeft() {
        return game.getPlayersLeft();
    }

    public boolean hasTimePassed(int seconds) {
        long millis = (seconds * 1000L);
        return gameTime >= millis;
    }

    public void addGameTeam(GameTeam gameTeam) {
        game.addGameTeam(gameTeam);
    }

    public void removeGameTeam(GameTeam gameTeam) {
        game.removeGameTeam(gameTeam);
    }

    public boolean hasStarted() {
        return game.hasStarted();
    }

    public boolean hasEnded() {
        return game.hasEnded();
    }

    public boolean isRestarting() {
        return game.isRestarting();
    }

    public boolean isOpen() {
        return !game.isJoinable() && getOnlinePlayers() < getMaximumPlayers();
    }

    public boolean isSoloGame() {
        return gameConfig.isSoloGame();
    }

    public void openInventory(Player player, InventoryUI inventoryUI) {
        menuManager.openInventory(player, inventoryUI);
    }

    public World getGameWorld() {
        return gameConfig.getWorld();
    }

    public FeatureHandler getFeatureHandler() {
        return plugin.getFeatureHandler();
    }

    public void log(String message) {
        log(plugin, message);
    }
}
