package com.ultimismc.skywars.core.game.features.cosmetics.deathcries.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCry;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class SquealDeathCry extends DeathCry {

    private final PurchasableDesign design = new PurchasableDesign(Material.RABBIT_FOOT);

    public SquealDeathCry() {
        super("Squeal", CosmeticRarity.RARE, "entity.rabbit.death");
    }
}
