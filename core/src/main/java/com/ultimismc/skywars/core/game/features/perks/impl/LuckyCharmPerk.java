package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class LuckyCharmPerk extends AbstractPerk {

    public LuckyCharmPerk() {
        super(Material.SPECKLED_MELON, "Lucky Charm", PerkRarity.COMMON,
                "30% chance to get a Golden Apple after a kill.");
    }
}
