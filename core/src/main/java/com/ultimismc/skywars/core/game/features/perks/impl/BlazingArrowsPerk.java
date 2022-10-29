package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class BlazingArrowsPerk extends AbstractPerk {

    public BlazingArrowsPerk() {
        super(Material.BLAZE_POWDER, "Blazing Arrows", PerkRarity.RARE,
                "15% chance of shooting a fire arrow with a bow.");
    }
}
