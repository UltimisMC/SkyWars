package com.ultimismc.skywars.core.game.features;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.kits.KitManager;
import com.ultimismc.skywars.core.game.features.level.LevelManager;
import com.ultimismc.skywars.core.game.features.perks.PerkManager;
import lombok.Getter;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.InventoryUI;

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

    private final PurchasableHandler purchasableHandler;

    private final List<FeatureInitializer> featureInitializers = new ArrayList<>();

    public FeatureHandler(SkyWarsPlugin plugin) {
        this.plugin = plugin;

        kitManager = new KitManager(plugin);
        perkManager = new PerkManager(plugin);
        levelManager = new LevelManager(plugin);
        purchasableHandler = new PurchasableHandler();

        addInitializers(kitManager, perkManager, levelManager);
    }

    public void initializeFeatures() {
        for(FeatureInitializer featureInitializer : featureInitializers) {
            initializeFeature(featureInitializer);
        }

        purchasableHandler.registerPurchasableRepository(kitManager);
        purchasableHandler.registerPurchasableRepository(perkManager);
    }

    public void initializeFeature(FeatureInitializer featureInitializer) {
        plugin.log("Initializing " + featureInitializer.getName() + "...");
        featureInitializer.initializeFeature(plugin);
        plugin.getCommandHandler().registerDependency(featureInitializer.getClass(), featureInitializer);
    }

    public <T extends Purchasable> T getPurchasable(Class<T> clazz, String key) {
        return purchasableHandler.getPurchasable(clazz, key);
    }

    public Collection<Purchasable> getAllPurchasables() {
        return purchasableHandler.getAllPurchasables();
    }

    public void addInitializers(FeatureInitializer... identifiers) {
        featureInitializers.addAll(Arrays.asList(identifiers));
    }
}
