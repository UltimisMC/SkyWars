package com.ultimismc.skywars.core.game.features.cosmetics;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.game.features.PurchasableFeature;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.CageHandler;
import lombok.Getter;

/**
 * @author DirectPlan
 */
public class CosmeticManager implements FeatureInitializer {

    @Getter private final String name = "Cosmetics";

    private final FeatureHandler featureHandler;
    @Getter private final CageHandler cageHandler;

    public CosmeticManager(FeatureHandler featureHandler, SkyWarsPlugin plugin) {
        this.featureHandler = featureHandler;
        cageHandler = new CageHandler(plugin);

        registerCosmeticHandler(cageHandler);
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {


    }

    public <T extends PurchasableFeature<?>> void registerCosmeticHandler(T cosmeticHandler) {
        featureHandler.addInitializers(cosmeticHandler);
    }
}
