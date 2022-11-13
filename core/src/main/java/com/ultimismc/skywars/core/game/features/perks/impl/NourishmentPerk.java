package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class NourishmentPerk extends AbstractPerk {

    public NourishmentPerk() {
        super(Material.BREAD, "Nourishment", PerkRarity.COMMON,
                Arrays.asList("&7Every kill gives you full hunger",
                        "&7and saturation."));
    }
}
