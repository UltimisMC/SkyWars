package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class BaseballPlayerKit extends AbstractKit {

    public BaseballPlayerKit() {
        super(Material.CHAINMAIL_HELMET, "Baseball Player", KitRarity.RARE);
    }
}
