package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class RevengePerk extends AbstractPerk {

    public RevengePerk() {
        super(Material.IRON_SWORD, "Revenge", PerkRarity.LEGENDARY,
                "&7Spawn a spider when you die.");
    }
}
