package com.ultimismc.skywars.core.config;

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
@Getter
public enum CageConfigKeys implements ConfigEntry {

    SERIALIZED_CAGES_DATA("serialized-cages-data",
            Arrays.asList("Default/GLASS/0/COMMON/default.schematic")),

    ;
    private final String key;
    private final boolean forcedEntryDeclaration;
    @Setter
    private Object value;
    private final Map<String, ReplacementBoundary> replacementBoundaries = new HashMap<>();

    CageConfigKeys(String key, Object defaultValue, boolean overwrite) {
        this.key = key;
        this.value = defaultValue;

        this.forcedEntryDeclaration = overwrite;
    }

    CageConfigKeys(String key, Object value) {
        this(key, value, true);
    }
}
