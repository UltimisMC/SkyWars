package com.ultimismc.skywars.core.game.features.kits;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public class KitManager implements FeatureInitializer {

    private final String name = "Perks";

    private final Map<String, Kit> kits = new HashMap<>();

    public KitManager(SkyWarsPlugin plugin) {


    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {}

    public void registerKit(Kit kit) {
        kits.put(kit.getName(), kit);
    }

    public Kit getKit(String name) {
        return kits.get(name);
    }
}
