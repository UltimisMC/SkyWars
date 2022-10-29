package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class FrogKit extends AbstractKit {

    public FrogKit() {
        super(Material.SLIME_BALL, "Frog", KitRarity.COMMON);
    }
}
