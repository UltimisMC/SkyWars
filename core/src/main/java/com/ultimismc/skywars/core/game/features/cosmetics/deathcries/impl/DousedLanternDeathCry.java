package com.ultimismc.skywars.core.game.features.cosmetics.deathcries.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCry;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.Sound;

/**
 * @author DirectPlan
 */
@Getter
public class DousedLanternDeathCry extends DeathCry {

    private final PurchasableDesign design = new PurchasableDesign(Material.WATER_BUCKET);

    public DousedLanternDeathCry() {
        super("Doused Lantern", CosmeticRarity.RARE, Sound.CREEPER_DEATH);
    }
}
