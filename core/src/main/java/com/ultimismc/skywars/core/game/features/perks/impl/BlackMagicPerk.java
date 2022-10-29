package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class BlackMagicPerk extends AbstractPerk {

    public BlackMagicPerk() {
        super(Material.CAULDRON_ITEM, "Black Magic", PerkRarity.LEGENDARY,
                "30% chance to get an enderpearl after throwing a player in the void.");
    }
}
