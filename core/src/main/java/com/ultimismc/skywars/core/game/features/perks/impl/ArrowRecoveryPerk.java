package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class ArrowRecoveryPerk extends AbstractPerk {

    public ArrowRecoveryPerk() {
        super(Material.ARROW, "Arrow Recovery", PerkRarity.RARE,
                Arrays.asList("&750% chance of getting your arrow",
                        "&7back on bow hit."));
    }
}
