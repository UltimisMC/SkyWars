package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class SpeedBoostPerk extends AbstractPerk {

    private final PurchasableDesign design = new PurchasableDesign(Material.BREWING_STAND_ITEM);

    public SpeedBoostPerk() {
        super("Speed Boost", PerkRarity.RARE,
                Arrays.asList("&7Get Haste II for 300s when the",
                        "&7game starts."));
    }
}
