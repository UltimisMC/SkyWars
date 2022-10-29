package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class PigRiderKit extends AbstractKit {

    public PigRiderKit() {
        super(Material.CARROT_STICK, "Pig Rider", KitRarity.RARE);
    }
}
