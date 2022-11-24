package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class ArmorsmithKit extends AbstractKit {

    public ArmorsmithKit() {
        super(Material.ANVIL, "Armorsmith", KitRarity.COMMON);
    }

    @Override
    public boolean isDefault() {
        return true;
    }
}
