package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class GrenadeKit extends AbstractKit {

    public GrenadeKit() {
        super(Material.MONSTER_EGG, 50, "Grenade", KitRarity.COMMON);
    }
}
