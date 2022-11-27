package com.ultimismc.skywars.core.game.features;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.kits.KitManager;
import com.ultimismc.skywars.core.game.features.level.LevelManager;
import com.ultimismc.skywars.core.game.features.perks.PerkManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    private final CosmeticManager cosmeticManager;

    private final PurchasableHandler purchasableHandler;

    private final List<FeatureInitializer> featureInitializers = new ArrayList<>();

    public FeatureHandler(SkyWarsPlugin plugin) {
        this.plugin = plugin;

        kitManager = new KitManager();
        perkManager = new PerkManager();
        levelManager = new LevelManager(plugin);
        cosmeticManager = new CosmeticManager(this, plugin);
        purchasableHandler = new PurchasableHandler();

        addInitializers(kitManager, perkManager, levelManager, cosmeticManager);
    }

    public void initializeFeatures() {
        for(FeatureInitializer featureInitializer : featureInitializers) {
            initializeFeature(featureInitializer);
        }
    }

    public void shutdownFeatures() {
        for(FeatureInitializer featureInitializer : featureInitializers) {
            shutdownFeature(featureInitializer);
        }
    }

    public void initializeFeature(FeatureInitializer featureInitializer) {
        plugin.log("Initializing " + featureInitializer.getName() + "...");
        featureInitializer.initializeFeature(plugin);
        plugin.getCommandHandler().registerDependency(featureInitializer.getClass(), featureInitializer);

        if(featureInitializer instanceof PurchasableRepository<?>) {
            PurchasableRepository<?> purchasableRepository = (PurchasableRepository<?>) featureInitializer;
            purchasableHandler.registerPurchasableRepository(purchasableRepository);
        }
    }

    public void shutdownFeature(FeatureInitializer featureInitializer) {
        plugin.log("Shutting down " + featureInitializer.getName() + "...");
        featureInitializer.shutdownFeature(plugin);
    }

    public <T extends Purchasable> T getPurchasable(Class<T> clazz, String key) {
        return purchasableHandler.getPurchasable(clazz, key);
    }

    public Collection<Purchasable> getAllPurchasables() {
        return purchasableHandler.getAllPurchasables();
    }

    public List<Purchasable> getDefaultPurchasables() {
        return purchasableHandler.getDefaultPurchasables();
    }

    public void addInitializers(FeatureInitializer... identifiers) {
        featureInitializers.addAll(Arrays.asList(identifiers));
    }
}
