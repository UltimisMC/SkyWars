package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.game.GameStatistics;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.level.Level;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author DirectPlan
 */
@Getter
@Setter
public class UserStatistics {

    private Level level;
    private int nextLevelProgress;
    private int totalExp = 0;

    private int coins, souls;
    private int maximumSouls = 150;

    private final Map<TeamType, GameStatistics> gameStats = new HashMap<>();

    public UserStatistics() {
        for(TeamType teamType : TeamType.values()) {
            gameStats.put(teamType, new GameStatistics());
        }
    }

    public GameStatistics getStatistics(TeamType gameType) {
        return gameStats.get(gameType);
    }

    public GameStatistics getSoloStatistics() {
        return getStatistics(TeamType.SOLO);
    }

    public GameStatistics getDoublesStatistics() {
        return getStatistics(TeamType.DOUBLES);
    }

    public void increaseExp(int increase) {
        totalExp += increase;
    }

    public void increaseCoins(int coins) {
        this.coins += coins;
    }

    public void decreaseCoins(int coins) {
        if(this.coins < coins) return;
        this.coins -= coins;
    }

    public void increaseSouls(int souls) {
        this.souls += souls;
    }

    public void decreaseSouls(int souls) {
        if(this.souls < souls) return;
        this.souls -= souls;
    }

    public int getSoloWins() {
        GameStatistics soloStatistics = getSoloStatistics();
        if(soloStatistics == null) return 0;
        return soloStatistics.getWins();
    }

    public int getDoublesWins() {
        GameStatistics doublesStatistics = getDoublesStatistics();
        if(doublesStatistics == null) return 0;

        return doublesStatistics.getWins();
    }

    public int getSoloKills() {
        GameStatistics soloStatistics = getSoloStatistics();
        if(soloStatistics == null) return 0;

        return soloStatistics.getKills();
    }

    public int getDoublesKills() {
        GameStatistics doublesStatistics = getDoublesStatistics();
        if(doublesStatistics == null) return 0;

        return doublesStatistics.getKills();
    }

    public int getTotalWins() {
        return getTotalStat(GameStatistics::getWins);
    }

    public int getTotalLosses() {
        return getTotalStat(GameStatistics::getLosses);
    }

    public int getTotalKills() {
        return getTotalStat(GameStatistics::getKills);
    }

    public int getTotalAssists() {
        return getTotalStat(GameStatistics::getAssists);
    }

    public int getTotalDeaths() {
        return getTotalStat(GameStatistics::getDeaths);
    }

    public int getTotalBowKills() {
        return getTotalStat(GameStatistics::getBowKills);
    }

    public int getTotalVoidKills() {
        return getTotalStat(GameStatistics::getVoidKills);
    }

    public int getTotalArrowShot() {
        return getTotalStat(GameStatistics::getArrowsShot);
    }

    public int getTotalArrowHits() {
        return getTotalStat(GameStatistics::getArrowsHit);
    }

    public int getTotalChestsOpened() {
        return getTotalStat(GameStatistics::getChestsOpened);
    }

    public int getTotalWinstreak() {
        return getTotalStat(GameStatistics::getWinstreak);
    }

    public int getTotalBestWinstreak() {
        return getTotalStat(GameStatistics::getBestWinstreak);
    }

    private int getTotalStat(Function<GameStatistics, Integer> statFunction) {
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += statFunction.apply(gameStatistics);
        }
        return value;
    }
}
