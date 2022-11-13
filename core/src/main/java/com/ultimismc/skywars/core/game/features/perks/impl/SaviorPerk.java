package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class SaviorPerk extends AbstractPerk {

    public SaviorPerk() {
        super(Material.APPLE, "Savior", PerkRarity.COMMON,
                Arrays.asList("&7Enemy kills give you absorption I",
                        "&7for 7 seconds."));
    }
}
