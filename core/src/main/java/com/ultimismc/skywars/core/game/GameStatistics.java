package com.ultimismc.skywars.core.game;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Getter
@Setter
public class GameStatistics {

    private int wins, losses;

    private int kills, deaths;
    private int bowKills, voidKills;
    private int arrowsShot, arrowsHit;
    private int chestsOpened;
}
