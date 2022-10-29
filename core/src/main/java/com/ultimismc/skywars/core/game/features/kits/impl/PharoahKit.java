package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class PharoahKit extends AbstractKit {

    public PharoahKit() {
        super(Material.GOLD_HELMET, "Pharoah", KitRarity.RARE);
    }
}
