package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class ArrowRecoveryPerk extends AbstractPerk {

    public ArrowRecoveryPerk() {
        super(Material.ARROW, "Arrow Recovery", PerkRarity.RARE,
                "50% chance of getting your arrow back on bow hit.");
    }
}
