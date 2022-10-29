package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class MagicianKit extends AbstractKit {

    public MagicianKit() {
        super(Material.POTION, 16398, "Magician", KitRarity.RARE);
    }
}
