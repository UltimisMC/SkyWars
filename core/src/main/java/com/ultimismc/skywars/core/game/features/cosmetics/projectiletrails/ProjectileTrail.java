package com.ultimismc.skywars.core.game.features.cosmetics.projectiletrails;

import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.entity.Projectile;

/**
 * @author DirectPlan
 */
@Getter
public abstract class ProjectileTrail extends Cosmetic {

    private final String category = "Projectile Trail";

    public ProjectileTrail(String name, CosmeticRarity rarity) {
        super(name, rarity);

        addDescription("Selects the " + getNameWithCategory() + ".");
    }

    public abstract void playProjectileTrail(User user, Projectile projectile);
}
