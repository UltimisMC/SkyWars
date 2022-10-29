package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class FishermanKit extends AbstractKit {

    public FishermanKit() {
        super(Material.FISHING_ROD, "Fisherman", KitRarity.RARE);
    }
}
