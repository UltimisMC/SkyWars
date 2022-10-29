package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class AnnoyOMitePerk extends AbstractPerk {

    public AnnoyOMitePerk() {
        super(Material.MONSTER_EGG, 60, "Annoy-o-mite", PerkRarity.RARE,
                "10% chance to spawn a SilverFish next to enemies when you hit them " +
                        "with a boy");
    }
}
