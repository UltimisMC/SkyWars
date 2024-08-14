package com.ultimismc.skywars.core.game.features.cosmetics.cages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.config.ConfigEntry;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
@AllArgsConstructor
public enum CageConfigKeys implements ConfigEntry {

    SERIALIZED_CAGES_DATA("serialized-cages-data",
            Arrays.asList("Default/GLASS/0/COMMON/default.schematic")),

    ;
    private final String key;
    @Setter
    private Object value;
}
