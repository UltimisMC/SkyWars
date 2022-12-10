package com.ultimismc.skywars.core.game.features.cosmetics.killeffects;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.impl.DefaultKillEffect;
import com.ultimismc.skywars.core.user.User;
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

    public void playKillEffect(User user, User killer) {
        if(killer == null) return;
        KillEffect killEffect = killer.getSetting(KillEffect.class, settingKey);
        killEffect.playKillEffect(user, killer);
    }
}
