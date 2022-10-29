package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class EcologistKit extends AbstractKit {

    public EcologistKit() {
        super(Material.IRON_AXE, "Ecologist", KitRarity.COMMON);
    }
}
