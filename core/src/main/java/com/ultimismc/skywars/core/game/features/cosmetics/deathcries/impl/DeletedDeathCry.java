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
public class DeletedDeathCry extends DeathCry {

    private final PurchasableDesign design = new PurchasableDesign(Material.DEAD_BUSH);

    public DeletedDeathCry() {
        super("Deleted", CosmeticRarity.RARE, Sound.SPIDER_DEATH);
    }
}
