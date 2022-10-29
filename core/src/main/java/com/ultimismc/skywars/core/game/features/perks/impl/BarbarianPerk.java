package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class BarbarianPerk extends AbstractPerk {

    public BarbarianPerk() {
        super(Material.IRON_AXE, "Barbarian", PerkRarity.LEGENDARY,
                "Gain a Sharpness level after getting 3 Axe Kills.");
    }
}
