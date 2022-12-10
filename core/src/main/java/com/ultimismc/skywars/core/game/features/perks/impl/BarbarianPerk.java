package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class BarbarianPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_AXE);

    public BarbarianPerk() {
        super("Barbarian", PerkRarity.LEGENDARY,
                Arrays.asList("&7Gain a Sharpness level after",
                        "&7getting 3 Axe Kills."));
    }
}
