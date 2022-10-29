package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class SpeedBoostPerk extends AbstractPerk {

    public SpeedBoostPerk() {
        super(Material.BREWING_STAND_ITEM, "Speed Boost", PerkRarity.RARE,
                "Get haste II for 300s when the game starts.");
    }
}
