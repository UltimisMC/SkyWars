package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class FatPerk extends AbstractPerk {

    public FatPerk() {
        super(Material.GOLDEN_APPLE, "Fat", PerkRarity.RARE,
                "Gain 20s of Absorption I when the game starts.");
    }
}
