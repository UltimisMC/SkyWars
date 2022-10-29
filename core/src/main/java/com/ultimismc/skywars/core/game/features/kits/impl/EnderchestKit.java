package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class EnderchestKit extends AbstractKit {

    public EnderchestKit() {
        super(Material.ENDER_CHEST, "Enderchest", KitRarity.RARE);
    }
}
