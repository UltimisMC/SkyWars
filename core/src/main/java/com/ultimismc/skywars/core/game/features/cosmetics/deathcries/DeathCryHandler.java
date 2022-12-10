package com.ultimismc.skywars.core.game.features.cosmetics.deathcries;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.impl.DefaultDeathCry;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class DeathCryHandler extends PurchasableRegistry<DeathCry> {

    private final String name = "Death Cries";

    public DeathCryHandler() {
        super("deathcry");
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPurchasable(new DefaultDeathCry());

        super.initializeFeature(plugin);
    }

    public void playDeathCry(User user) {
        DeathCry deathCry = user.getSetting(DeathCry.class, settingKey);
        deathCry.playDeathCry(user);
    }
}
