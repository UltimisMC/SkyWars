package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class HunterKit extends AbstractKit {

    public HunterKit() {
        super(Material.BOW, "Hunter", KitRarity.RARE);
    }
}
