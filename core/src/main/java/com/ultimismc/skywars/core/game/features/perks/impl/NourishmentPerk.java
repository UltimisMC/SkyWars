package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class NourishmentPerk extends AbstractPerk {

    public NourishmentPerk() {
        super(Material.BREAD, "Nourishment", PerkRarity.COMMON,
                "Every kill gives you full hunger and saturation.");
    }
}
