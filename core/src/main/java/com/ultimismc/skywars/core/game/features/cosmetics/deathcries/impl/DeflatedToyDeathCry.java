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
public class DeflatedToyDeathCry extends DeathCry {

    private final PurchasableDesign design = new PurchasableDesign(Material.RAW_FISH, 3);

    public DeflatedToyDeathCry() {
        super("Deflated Toy", CosmeticRarity.COMMON, Sound.BAT_DEATH);
    }

}
