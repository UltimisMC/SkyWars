package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class FrostPerk extends AbstractPerk {

    public FrostPerk() {
        super(Material.ICE, "Frost", PerkRarity.LEGENDARY,
                "40% chance to give a player slowness I for 3 seconds on fully charged bow hit.");
    }
}
