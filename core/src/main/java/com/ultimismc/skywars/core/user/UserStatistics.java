package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.game.GameStatistics;
import com.ultimismc.skywars.core.game.TeamType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
@Setter
public class UserStatistics {

    private int level;
    private int coins, souls;

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
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += gameStatistics.getWins();
        }
        return value;
    }

    public int getTotalLosses() {
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += gameStatistics.getLosses();
        }
        return value;
    }

    public int getTotalKills() {
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += gameStatistics.getKills();
        }
        return value;
    }

    public int getTotalDeaths() {
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += gameStatistics.getDeaths();
        }
        return value;
    }

    public int getTotalBowKills() {
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += gameStatistics.getBowKills();
        }
        return value;
    }

    public int getTotalVoidKills() {
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += gameStatistics.getVoidKills();
        }
        return value;
    }

    public int getTotalArrowShot() {
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += gameStatistics.getArrowsShot();
        }
        return value;
    }

    public int getTotalArrowHits() {
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += gameStatistics.getArrowsHit();
        }
        return value;
    }

    public int getTotalChestsOpened() {
        int value = 0;
        for(GameStatistics gameStatistics : gameStats.values()) {
            value += gameStatistics.getChestsOpened();
        }
        return value;
    }
}
