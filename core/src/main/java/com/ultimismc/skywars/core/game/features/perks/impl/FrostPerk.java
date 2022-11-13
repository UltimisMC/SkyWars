package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class FrostPerk extends AbstractPerk {

    public FrostPerk() {
        super(Material.ICE, "Frost", PerkRarity.LEGENDARY,
                Arrays.asList("&740% chance to give a player",
                        "&7slowness I for 3 seconds on",
                        "&7fully charged bow hit."));
    }
}
