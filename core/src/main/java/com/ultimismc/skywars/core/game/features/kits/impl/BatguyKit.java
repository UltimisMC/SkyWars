package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class BatguyKit extends AbstractKit {

    public BatguyKit() {
        super(Material.LEATHER_HELMET, "Batguy", KitRarity.COMMON);
    }

    @Override
    public boolean isDefault() {
        return true;
    }
}
