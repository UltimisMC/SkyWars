package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class AnnoyOMitePerk extends AbstractPerk {

    public AnnoyOMitePerk() {
        super(Material.MONSTER_EGG, 60, "Annoy-o-mite", PerkRarity.RARE,
                Arrays.asList("&710% chance to spawn SilverFish",
                        "&7next to enemies when you hit",
                        "&7them with a bow."));
    }
}
