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
public class GrumbleDeathCry extends DeathCry {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJhM2U2MmM1ODEwYTU2N2MyNjU0ZGFmN2FlMTM3NjVlN2JjOTY0ZTRmZjEyNGE5YWE2NjA4MDI2NTU0YjdhIn19fQ==");

    public GrumbleDeathCry() {
        super("Grumble", CosmeticRarity.RARE, Sound.ZOMBIE_PIG_DEATH);
    }
}
