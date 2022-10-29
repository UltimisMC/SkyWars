package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class SaviorPerk extends AbstractPerk {

    public SaviorPerk() {
        super(Material.APPLE, "Savior", PerkRarity.COMMON,
                "Enemy kills give you absorption I for 7 seconds.");
    }
}
