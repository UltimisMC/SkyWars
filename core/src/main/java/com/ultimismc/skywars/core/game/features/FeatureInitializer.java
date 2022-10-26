package com.ultimismc.skywars.core.game.features;

import com.ultimismc.skywars.core.SkyWarsPlugin;

/**
 * @author DirectPlan
 */
public interface FeatureInitializer {

    String getName();

    void initializeFeature(SkyWarsPlugin plugin);
}
