package com.ultimismc.skywars.core.game.features.cosmetics.killeffects.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffect;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffectExecutor;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class DefaultKillEffect extends KillEffect {

    private final PurchasableDesign design = new PurchasableDesign(Material.BARRIER);

    public DefaultKillEffect() {
        super("None", CosmeticRarity.COMMON);

        addDescription("Selecting this option disables");
        addDescription("your Kill Effect.");
    }

    @Override
    public KillEffectExecutor killEffectExecutor(User target, User killer) {
        return new DefaultKillEffectExecutor(target, killer);
    }

    @Override
    public boolean isDefault() {
        return true;
    }
}

class DefaultKillEffectExecutor extends KillEffectExecutor {

    public DefaultKillEffectExecutor(User user, User killer) {
        super(user, killer, 1);
    }

    @Override
    public void animateEffect(int tick) {

    }
}
