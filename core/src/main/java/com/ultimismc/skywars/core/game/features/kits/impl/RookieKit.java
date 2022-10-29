package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class RookieKit extends AbstractKit {

    public RookieKit() {
        super(Material.LEATHER_HELMET, "Rookie", KitRarity.COMMON);
    }
}
