package com.ultimismc.skywars.core.game.features.cosmetics.deathcries;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.impl.*;
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
        registerPurchasable(new DeflatedToyDeathCry());
        registerPurchasable(new EndermanDeathCry());
        registerPurchasable(new DinosaurDeathCry());
        registerPurchasable(new RobotMouseDeathCry());
        registerPurchasable(new PigDeathCry());
        registerPurchasable(new FireballDeathCry());
        registerPurchasable(new DryBonesDeathCry());
        registerPurchasable(new DingDeathCry());
        registerPurchasable(new SplashDeathCry());
        registerPurchasable(new BatDeathCry());
        registerPurchasable(new PlopDeathCry());
        registerPurchasable(new GrumbleDeathCry());
        registerPurchasable(new DeletedDeathCry());
        registerPurchasable(new FragileDeathCry());
        registerPurchasable(new DisintegratedDeathCry());
        registerPurchasable(new WitherDeathCry());
        registerPurchasable(new SquealDeathCry());
        registerPurchasable(new DousedLanternDeathCry());
        registerPurchasable(new BazingaDeathCry());

        super.initializeFeature(plugin);
    }

    public void playDeathCry(User user) {
        DeathCry deathCry = user.getSetting(DeathCry.class, settingKey);
        deathCry.playDeathCry(user);
    }
}
