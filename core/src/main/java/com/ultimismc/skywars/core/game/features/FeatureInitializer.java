package com.ultimismc.skywars.core.game.features;

import com.ultimismc.skywars.core.SkyWarsPlugin;

/**
 * @author DirectPlan
 */
public interface FeatureInitializer {

    String getName();

    void initializeFeature(SkyWarsPlugin plugin);

    default void shutdownFeature(SkyWarsPlugin plugin) {}

    default void log(SkyWarsPlugin plugin, String message) {
        plugin.log("[" + getName() + "] " + message);
    }
}
