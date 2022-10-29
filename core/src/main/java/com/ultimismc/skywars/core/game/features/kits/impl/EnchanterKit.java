package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class EnchanterKit extends AbstractKit {

    public EnchanterKit() {
        super(Material.ENCHANTMENT_TABLE, "Enchanter", KitRarity.RARE);
    }
}
