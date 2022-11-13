package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class NecromancerPerk extends AbstractPerk {

    public NecromancerPerk() {
        super(Material.ROTTEN_FLESH, "Necromancer", PerkRarity.LEGENDARY,
                Arrays.asList("&716% chance to spawn a friendly",
                        "&7Zombie on kill."));
    }
}
