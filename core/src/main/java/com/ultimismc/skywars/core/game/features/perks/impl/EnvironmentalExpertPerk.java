package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class EnvironmentalExpertPerk extends AbstractPerk {

    public EnvironmentalExpertPerk() {
        super(Material.LEATHER_CHESTPLATE, "Environmental Expert", PerkRarity.RARE,
                "Reduces environmental damage by 50%");
    }
}
