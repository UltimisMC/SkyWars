package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class ScoutKit extends AbstractKit {

    public ScoutKit() {
        super(Material.POTION, 2,"Scout", KitRarity.COMMON);
    }
}
