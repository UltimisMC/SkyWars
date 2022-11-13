package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class BlazingArrowsPerk extends AbstractPerk {

    public BlazingArrowsPerk() {
        super(Material.BLAZE_POWDER, "Blazing Arrows", PerkRarity.RARE,
                Arrays.asList("&715% chance of shooting a fire",
                        "&7arrow with a bow."));
    }
}
