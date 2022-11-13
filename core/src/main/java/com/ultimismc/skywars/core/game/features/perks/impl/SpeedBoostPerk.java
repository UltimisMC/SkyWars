package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class SpeedBoostPerk extends AbstractPerk {

    public SpeedBoostPerk() {
        super(Material.BREWING_STAND_ITEM, "Speed Boost", PerkRarity.RARE,
                Arrays.asList("&7Get Haste II for 300s when the",
                        "&7game starts."));
    }
}
