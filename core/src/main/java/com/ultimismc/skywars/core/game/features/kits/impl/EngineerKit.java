package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class EngineerKit extends AbstractKit {

    public EngineerKit() {
        super(Material.REDSTONE, "Engineer", KitRarity.RARE);
    }
}
