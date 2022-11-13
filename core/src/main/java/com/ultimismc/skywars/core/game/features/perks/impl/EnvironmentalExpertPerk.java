package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class EnvironmentalExpertPerk extends AbstractPerk {

    public EnvironmentalExpertPerk() {
        super(Material.LEATHER_CHESTPLATE, "Environmental Expert", PerkRarity.RARE,
                Arrays.asList("&7Reduces environmental damage by",
                        "&750%"));
    }
}
