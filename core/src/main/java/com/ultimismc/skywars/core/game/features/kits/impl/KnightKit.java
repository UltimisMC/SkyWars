package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class KnightKit extends AbstractKit {

    public KnightKit() {
        super(Material.IRON_SWORD, "Knight", KitRarity.COMMON);
    }
}
