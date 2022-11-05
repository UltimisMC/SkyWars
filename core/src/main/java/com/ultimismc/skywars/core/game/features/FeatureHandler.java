package com.ultimismc.skywars.core.game.features;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.kits.KitManager;
import com.ultimismc.skywars.core.game.features.level.LevelManager;
import com.ultimismc.skywars.core.game.features.perks.PerkManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public class FeatureHandler {

    private final SkyWarsPlugin plugin;

    private final KitManager kitManager;
    private final PerkManager perkManager;
    private final LevelManager levelManager;

    private final List<FeatureInitializer> featureInitializers = new ArrayList<>();

    public FeatureHandler(SkyWarsPlugin plugin) {
        this.plugin = plugin;

        kitManager = new KitManager(plugin);
        perkManager = new PerkManager(plugin);
        levelManager = new LevelManager(plugin);

        addInitializers(kitManager, perkManager, levelManager);
    }

    public void initializeFeatures() {
        for(FeatureInitializer featureInitializer : featureInitializers) {
            plugin.log("Initializing " + featureInitializer.getName() + "...");
            featureInitializer.initializeFeature(plugin);
            plugin.getCommandHandler().registerDependency(featureInitializer.getClass(), featureInitializer);
        }

    }

    public void addInitializers(FeatureInitializer... identifiers) {
        featureInitializers.addAll(Arrays.asList(identifiers));
    }
}
