package com.ultimismc.skywars.core.game.features.cosmetics.deathcries.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCry;
import lombok.Getter;
import org.bukkit.Sound;

/**
 * @author DirectPlan
 */
@Getter
public class DingDeathCry extends DeathCry {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzczZmIyYTI4ZjNiNjY1NDg2NDEyZGI3YjY2Y2RiN2ZjNWM3ZDMzZDc0ZTg1MGI5NDcyMmRkM2QxNGFhYSJ9fX0=");


    public DingDeathCry() {
        super("Ding", CosmeticRarity.RARE, Sound.SUCCESSFUL_HIT);
    }
}
