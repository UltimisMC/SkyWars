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
public class DryBonesDeathCry extends DeathCry {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2U5YmE4ZDY3ZTQxNjFlZjhlNzQyZmI3YmYxOThiNjE0MTJmMjRjMmY4MmI1NDU4MTA4Y2RlOTkyOTNlNzdhMSJ9fX0=");

    public DryBonesDeathCry() {
        super("Dry Bones", CosmeticRarity.COMMON, Sound.SKELETON_DEATH);
    }
}
