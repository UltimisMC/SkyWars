package com.ultimismc.skywars.core.game.features.cosmetics;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.CageHandler;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCryHandler;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffectHandler;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageHandler;
import com.ultimismc.skywars.core.game.features.cosmetics.projectiletrails.ProjectileTrailHandler;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class CosmeticManager implements FeatureInitializer {

    private final String name = "Cosmetics";

    private final FeatureHandler featureHandler;
    private final CageHandler cageHandler;
    private final KillMessageHandler killMessageHandler;
    private final KillEffectHandler killEffectHandler;
    private final ProjectileTrailHandler projectileTrailHandler;
    private final DeathCryHandler deathCryHandler;

    public CosmeticManager(FeatureHandler featureHandler, SkyWarsPlugin plugin) {
        this.featureHandler = featureHandler;
        cageHandler = new CageHandler(plugin);
        killMessageHandler = new KillMessageHandler();
        killEffectHandler = new KillEffectHandler();
        projectileTrailHandler = new ProjectileTrailHandler();
        deathCryHandler = new DeathCryHandler();

        registerCosmeticHandler(cageHandler);
        registerCosmeticHandler(killMessageHandler);
        registerCosmeticHandler(killEffectHandler);
        registerCosmeticHandler(projectileTrailHandler);
        registerCosmeticHandler(deathCryHandler);
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {}

    public <T extends PurchasableRegistry<?>> void registerCosmeticHandler(T cosmeticHandler) {
        featureHandler.addInitializers(cosmeticHandler);
    }
}
