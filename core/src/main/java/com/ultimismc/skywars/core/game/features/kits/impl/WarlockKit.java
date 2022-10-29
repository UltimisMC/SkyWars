package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class WarlockKit extends AbstractKit {

    public WarlockKit() {
        super(Material.BREWING_STAND_ITEM, "Warlock", KitRarity.RARE);
    }
}
