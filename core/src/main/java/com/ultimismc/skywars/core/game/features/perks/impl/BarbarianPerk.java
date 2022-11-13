package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class BarbarianPerk extends AbstractPerk {

    public BarbarianPerk() {
        super(Material.IRON_AXE, "Barbarian", PerkRarity.LEGENDARY,
                Arrays.asList("&7Gain a Sharpness level after",
                        "&7getting 3 Axe Kills."));
    }
}
