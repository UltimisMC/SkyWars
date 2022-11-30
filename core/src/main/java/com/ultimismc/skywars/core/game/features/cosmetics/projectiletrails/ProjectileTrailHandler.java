package com.ultimismc.skywars.core.game.features.cosmetics.projectiletrails;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.projectiletrails.impl.DefaultProjectileTrail;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.entity.Projectile;

/**
 * @author DirectPlan
 */
@Getter
public class ProjectileTrailHandler extends PurchasableRegistry<ProjectileTrail> {

    private final String name = "Projectile Trails";

    public ProjectileTrailHandler() {
        super("projectiletrail");
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPurchasable(new DefaultProjectileTrail());

        super.initializeFeature(plugin);
    }

    public void playProjectileTrail(ProjectileTrail projectileTrail, User user, Projectile projectile) {


    }
}
