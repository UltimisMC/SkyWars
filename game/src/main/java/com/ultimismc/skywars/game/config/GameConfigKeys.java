package com.ultimismc.skywars.game.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.config.ConfigEntry;
import xyz.directplan.directlib.config.replacement.ReplacementBoundary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@AllArgsConstructor
@Getter
public enum GameConfigKeys implements ConfigEntry {

    SERVER_ID("server-id", "SW01"),
    GAME_TYPE("game-type", "NORMAL"),
    GAME_TEAM_TYPE("game-team-type", "SOLO"),

    GAME_MAXIMUM_PLAYERS("game.solo.maximum-players", 12),
    GAME_SOLO_MAXIMUM_PLAYERS("game.doubles.maximum-players", 24),

    ;

    private final String key;
    @Setter
    private Object value;
    private boolean forceEntryDeclaration;
    private final Map<String, ReplacementBoundary> replacementBoundaries = new HashMap<>();

    GameConfigKeys(String key, Object value) {
        this(key, value, true);
    }
}
