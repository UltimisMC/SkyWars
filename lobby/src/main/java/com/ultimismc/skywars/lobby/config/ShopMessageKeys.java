package com.ultimismc.skywars.lobby.config;

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
public enum ShopMessageKeys implements ConfigEntry {



    ;

    private final String key;
    @Setter
    private Object value;
    private boolean forceEntryDeclaration;
    private final Map<String, ReplacementBoundary> replacementBoundaries = new HashMap<>();

    ShopMessageKeys(String key, Object value) {
        this(key, value, true);
    }

}
