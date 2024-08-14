package com.ultimismc.skywars.game.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.config.ConfigEntry;

/**
 * @author DirectPlan
 */
@AllArgsConstructor
@Getter
public enum GameConfigKeys implements ConfigEntry {

    MAP_NAME("map-name", "map"),
    SERVER_ID("server-id", "SW01"),
    GAME_TYPE("game-type", "NORMAL"),
    GAME_TEAM_TYPE("game-team-type", "SOLO"),
    ;

    private final String key;
    @Setter
    private Object value;
}
