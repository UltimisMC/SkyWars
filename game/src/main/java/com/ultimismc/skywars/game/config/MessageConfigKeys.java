package com.ultimismc.skywars.game.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.config.ConfigEntry;
import xyz.directplan.directlib.config.replacement.ReplacementBoundary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@AllArgsConstructor
@Getter
public enum MessageConfigKeys implements ConfigEntry {

    SKYWARS_LOBBY_SCOREBOARD_DISPLAYNAME("skywars-game.scoreboard.display-name", "&b&lSKYWARS"),
    SKYWARS_LOBBY_SCOREBOARD_LINES("skywars-game.scoreboard.lines",
            Arrays.asList("&7%servertime_MM/dd/yyyy% &8L21G",
                    " ",
                    ""))

    ,
    ;


    private final String key;
    @Setter
    private Object value;
    private boolean forceEntryDeclaration;
    private final Map<String, ReplacementBoundary> replacementBoundaries = new HashMap<>();

    MessageConfigKeys(String key, Object value) {
        this(key, value, true);
    }
}
