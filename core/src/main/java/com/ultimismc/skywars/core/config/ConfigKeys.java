package com.ultimismc.skywars.core.config;

import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.config.ConfigEntry;
import xyz.directplan.directlib.config.replacement.ReplacementBoundary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public enum ConfigKeys implements ConfigEntry {

    /* config.yml keys */
    SPAWN_LOCATION("spawn-location", "0, 0, 0"),
    WORLD_NAME("world-name", "world"),

    STORAGE_HOST("mongodb.host", "localhost"),
    STORAGE_PORT("mongodb.port", 0),
    STORAGE_USERNAME("mongodb.username", "username"),
    STORAGE_PASSWORD("mongodb.password", "password"),
    STORAGE_DATABASE("mongodb.database", "database"),

    JEDIS_HOST("jedis.host", "localhost"),
    JEDIS_PORT("jedis.port", 6379),
    JEDIS_PASSWORD("jedis.password", null),
//    STORAGE_MAXIMUM_POOL_SIZE("storage.maximum-pool-size", 10),

    INCREMENTER_AMOUNT("progress-bar.incrementer-amount", 5),
    PROGRESSBAR_BARS("progress-bar.bars", 10),
    PROGRESSBAR_BARS_SYMBOL("progress-bar.bars-symbol", "■"),
    PROGRESSBAR_COLORS_STABLE("progress-bar.colors.stable", "&b"),
    PROGRESSBAR_COLORS_BALANCED("progress-bar.colors.balanced", "&b"),
    PROGRESSBAR_COLORS_LOW("progress-bar.colors.low", "&b"),
    PROGRESSBAR_COLORS_UNCOMPLETED("progress-bar.colors.un-completed", "&7"),

    ;
    private final String key;
    @Setter
    private Object value;
    private final Map<String, ReplacementBoundary> replacementBoundaries = new HashMap<>();

    ConfigKeys(String key, Object defaultValue) {
        this.key = key;
        this.value = defaultValue;
    }
}
