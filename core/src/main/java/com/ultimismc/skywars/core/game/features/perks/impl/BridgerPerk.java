package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class BridgerPerk extends AbstractPerk {

    public BridgerPerk() {
        super(Material.WOOD, "Bridger", PerkRarity.COMMON,
                Arrays.asList("&7Grants 50% chance to not consume",
                        "&7placeable blocks."));
    }
}
