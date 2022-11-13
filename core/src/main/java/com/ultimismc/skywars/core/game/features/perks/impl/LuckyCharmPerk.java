package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class LuckyCharmPerk extends AbstractPerk {

    public LuckyCharmPerk() {
        super(Material.SPECKLED_MELON, "Lucky Charm", PerkRarity.COMMON,
                Arrays.asList("&730% chance to get a Golden Apple",
                                "&7after a kill."));
    }
}
