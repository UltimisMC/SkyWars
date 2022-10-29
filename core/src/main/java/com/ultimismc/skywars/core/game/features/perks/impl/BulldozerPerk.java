package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class BulldozerPerk extends AbstractPerk {

    public BulldozerPerk() {
        super(Material.ANVIL, "Bulldozer", PerkRarity.LEGENDARY,
                "Enemy kills give you strength I for 5s in Solo (2s in Team Mode).");
    }
}
