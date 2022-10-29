package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class PrincessKit extends AbstractKit {

    public PrincessKit() {
        super(Material.RED_ROSE, "Princess", KitRarity.RARE);
    }
}
