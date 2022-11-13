package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class FatPerk extends AbstractPerk {

    public FatPerk() {
        super(Material.GOLDEN_APPLE, "Fat", PerkRarity.RARE,
                Arrays.asList("&7Gain 20s of absorption I when",
                        "&7the game starts."));
    }
}
