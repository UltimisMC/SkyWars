package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class FarmerKit extends AbstractKit {

    public FarmerKit() {
        super(Material.DIAMOND_LEGGINGS, "Farmer", KitRarity.RARE);
    }
}
