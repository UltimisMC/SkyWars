package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class EnergixKit extends AbstractKit {

    public EnergixKit() {
        super(Material.BREWING_STAND_ITEM, "Energix", KitRarity.COMMON);
    }
}
