package com.ultimismc.skywars.core.game.features.cosmetics.deathcries.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCry;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class RobotMouseDeathCry extends DeathCry {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQzNmQ0YzM0ZTExNTEzOTIxMjFhMTNmODAyZjgwNjkxMDMxOWZiZjU5OGY3YjhhNGFhZTg0Yzc1YWUzNjI1In19fQ==");

    public RobotMouseDeathCry() {
        super("Robot Mouse", CosmeticRarity.COMMON, "mob.guardian.curse");
    }
}
