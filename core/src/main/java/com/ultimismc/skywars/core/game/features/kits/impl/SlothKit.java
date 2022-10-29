package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class SlothKit extends AbstractKit {

    public SlothKit() {
        super(Material.POTION, 9, "Sloth", KitRarity.RARE);
    }
}
