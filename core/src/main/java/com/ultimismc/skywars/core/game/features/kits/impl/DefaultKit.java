package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class DefaultKit extends AbstractKit {

    public DefaultKit() {
        super(Material.WOOD_PICKAXE, "Default", KitRarity.COMMON);
    }

    @Override
    public boolean isDefault() {
        return true;
    }
}
