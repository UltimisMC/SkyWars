package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class ResistanceBoostPerk extends AbstractPerk {

    public ResistanceBoostPerk() {
        super(Material.IRON_CHESTPLATE, "Resistance Boost", PerkRarity.COMMON,
                "Gain 15s of resistance II when the game starts.");
    }
}
