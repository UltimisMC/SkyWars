package com.ultimismc.skywars.core.game.features.cosmetics.killeffects;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.impl.DefaultKillEffect;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.impl.LightingStrikeKillEffect;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class KillEffectHandler extends PurchasableRegistry<KillEffect> {

    private final String name = "Kill Effects";
    private final SkyWarsPlugin plugin;

    public KillEffectHandler(SkyWarsPlugin plugin) {
        super("killeffect");

        this.plugin = plugin;
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPurchasable(new DefaultKillEffect());
        registerPurchasable(new LightingStrikeKillEffect());

        super.initializeFeature(plugin);
    }

    public void playKillEffect(User user, User killer) {
        if(killer == null) return;
        KillEffect killEffect = killer.getSetting(KillEffect.class, settingKey);

        KillEffectExecutor executor = killEffect.killEffectExecutor(user, killer);

        int period = (executor.getPeriodTicks() / executor.getFrequency());
        plugin.getServer().getScheduler().runTaskTimer(plugin, (Runnable) executor, 0, period);
    }
}
