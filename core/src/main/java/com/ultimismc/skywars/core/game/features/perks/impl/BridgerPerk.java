package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class BridgerPerk extends AbstractPerk {

    public BridgerPerk() {
        super(Material.WOOD, "Bridger", PerkRarity.COMMON,
                "Grants 50% chance to not consume placeable blocks.");
    }
}
