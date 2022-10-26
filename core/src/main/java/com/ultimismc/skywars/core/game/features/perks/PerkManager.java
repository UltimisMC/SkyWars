package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public class PerkManager implements FeatureInitializer {

    private final Map<String, Perk> perks = new HashMap<>();
    private final String name = "Perks";

    public PerkManager(SkyWarsPlugin plugin) {


    }

    public void registerPerk(Perk perk) {

    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {

    }
}
