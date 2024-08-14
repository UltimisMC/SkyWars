package com.ultimismc.skywars.game.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.config.ConfigEntry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@AllArgsConstructor
@Getter
public enum MapConfigKeys implements ConfigEntry {

    MAP_NAME("name", "Ehh"),
    SPECTATOR_SPAWN_LOCATION("spectator-spawn-location", "0, 0, 0"),
    MAP_SERIALIZED_ISLANDS("serialized-islands", Arrays.asList("")),
    MAP_SERIALIZED_CHESTS("serialized-chests", Arrays.asList("")),
    ;

    private final String key;
    @Setter
    private Object value;
}
