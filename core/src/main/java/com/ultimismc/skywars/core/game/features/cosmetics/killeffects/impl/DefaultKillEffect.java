package com.ultimismc.skywars.core.game.features.cosmetics.killeffects.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffect;
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
    }

    @Override
    public void playKillEffect(User target, User killer) {

    }

    @Override
    public boolean isDefault() {
        return true;
    }
}
