package com.ultimismc.skywars.core.game.features.cosmetics.killeffects;

import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public abstract class KillEffect extends Cosmetic {

    private final String category = "Kill Effect";

    public KillEffect(String name, CosmeticRarity rarity) {
        super(name, rarity);
    }

    public abstract void playKillEffect(User target, User killer);
}
