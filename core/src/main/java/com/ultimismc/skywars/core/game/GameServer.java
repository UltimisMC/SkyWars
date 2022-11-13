package com.ultimismc.skywars.core.game;

import com.ultimismc.skywars.core.game.map.Map;
import lombok.Data;

/**
 * @author DirectPlan
 */
@Data
public class GameServer {

    private final String serverId;
    private final GameType gameType;
    private final TeamType teamType;

    private final Map map;

    private int spectators;

    private boolean setupMode;

    public int getMaximumPlayers() {
        return teamType.getMaximumPlayers();
    }
}
