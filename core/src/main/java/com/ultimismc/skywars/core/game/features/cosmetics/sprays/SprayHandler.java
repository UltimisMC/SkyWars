package com.ultimismc.skywars.core.game.features.cosmetics.sprays;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class SprayHandler extends PurchasableRegistry<Spray> {

    private final String name = "Sprays";

    public SprayHandler() {
        super("spray");
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {


        super.initializeFeature(plugin);
    }
}
