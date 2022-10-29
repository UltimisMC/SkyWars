package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class SpeleologistKit extends AbstractKit {

    public SpeleologistKit() {
        super(Material.DIAMOND_PICKAXE, "Speleologist", KitRarity.RARE);
    }

}
