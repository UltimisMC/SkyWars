package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class JuggernautPerk extends AbstractPerk {

    public JuggernautPerk() {
        super(Material.DIAMOND_HELMET, "Juggernaut", PerkRarity.RARE,
                "Enemy kills give you regen I for 10 seconds.");
    }
}
