package com.ultimismc.skywars.core.game.features.cosmetics.killeffects;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.impl.DefaultKillEffect;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class KillEffectHandler extends PurchasableRegistry<KillEffect> {

    private final String name = "Kill Effects";

    public KillEffectHandler() {
        super("killeffect");
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPurchasable(new DefaultKillEffect());

        super.initializeFeature(plugin);
    }
}
