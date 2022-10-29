package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class KnowledgePerk extends AbstractPerk {

    public KnowledgePerk() {
        super(Material.BOOK, "Knowledge", PerkRarity.COMMON,
                "Every kill you gain 3 EXP Level.");
    }
}
