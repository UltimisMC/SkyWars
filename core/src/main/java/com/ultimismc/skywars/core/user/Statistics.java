package com.ultimismc.skywars.core.user;

/**
 * @author DirectPlan
 */
public interface Statistics {

    int getWins();

    int getLosses();

    int getWinstreak();

    int getBestWinstreak();

    int getKills();

    int getDeaths();

    int getAssists();

    int getBowKills();

    int getVoidKills();

    int getArrowsShot();

    int getArrowsHit();

    int getChestsOpened();
}
