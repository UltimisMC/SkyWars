package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class NecromancerPerk extends AbstractPerk {

    public NecromancerPerk() {
        super(Material.ROTTEN_FLESH, "Necromancer", PerkRarity.LEGENDARY,
                "16% chance to spawn a friendly Zombie on kill.");
    }
}
