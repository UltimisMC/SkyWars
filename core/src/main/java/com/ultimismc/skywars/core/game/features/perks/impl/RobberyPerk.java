package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class RobberyPerk extends AbstractPerk {

    public RobberyPerk() {
        super(Material.IRON_BARDING, "Barbarian", PerkRarity.LEGENDARY,
                "20% chance to drop a player's held item by hitting them with your fist.");
    }
}
