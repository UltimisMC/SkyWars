package com.ultimismc.skywars.core.game;

import com.ultimismc.skywars.core.game.map.Map;
import lombok.Data;

/**
 * @author DirectPlan
 */
@Data
public class GameServer {

    private final GameType gameType;
    private final TeamType teamType;

    private final Map map;

    private final int maximumPlayers;

    private int spectators;

}
