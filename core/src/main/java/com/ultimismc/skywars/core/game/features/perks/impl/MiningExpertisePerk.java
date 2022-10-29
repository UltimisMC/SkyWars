package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class MiningExpertisePerk extends AbstractPerk {

    public MiningExpertisePerk() {
        super(Material.IRON_PICKAXE, "Mining Expertise", PerkRarity.COMMON,
                "Get 1 extra ore per block mined.");
    }
}
