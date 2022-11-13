package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class RobberyPerk extends AbstractPerk {

    public RobberyPerk() {
        super(Material.IRON_BARDING, "Barbarian", PerkRarity.LEGENDARY,
                Arrays.asList("&720% chance to drop a player's",
                        "&7held item by hitting them with",
                        "&7your fist."));
    }
}
