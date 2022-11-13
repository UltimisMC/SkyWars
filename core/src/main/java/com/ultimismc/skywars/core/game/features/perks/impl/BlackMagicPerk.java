package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class BlackMagicPerk extends AbstractPerk {

    public BlackMagicPerk() {
        super(Material.CAULDRON_ITEM, "Black Magic", PerkRarity.LEGENDARY,
                Arrays.asList("&730% chance to get an enderpearl",
                        "&7after throwing a player in the",
                        "&7void."));
    }
}
