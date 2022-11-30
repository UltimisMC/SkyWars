package com.ultimismc.skywars.core.game.features.cosmetics.projectiletrails.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.projectiletrails.ProjectileTrail;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Projectile;

/**
 * @author DirectPlan
 */
@Getter
public class DefaultProjectileTrail extends ProjectileTrail {

    private final PurchasableDesign design = new PurchasableDesign(Material.SAND);

    public DefaultProjectileTrail() {
        super("Vanilla", CosmeticRarity.COMMON);
    }

    @Override
    public void playProjectileTrail(User user, Projectile projectile) {

    }

    @Override
    public boolean isDefault() {
        return true;
    }
}
