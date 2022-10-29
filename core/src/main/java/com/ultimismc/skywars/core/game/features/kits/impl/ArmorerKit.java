package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class ArmorerKit extends AbstractKit {

    public ArmorerKit() {
        super(Material.DIAMOND_CHESTPLATE, "Armorer", KitRarity.COMMON);
    }
}
