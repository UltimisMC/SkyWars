package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class HealerKit extends AbstractKit {

    public HealerKit() {
        super(Material.CAKE, "Healer", KitRarity.COMMON);
    }
}
