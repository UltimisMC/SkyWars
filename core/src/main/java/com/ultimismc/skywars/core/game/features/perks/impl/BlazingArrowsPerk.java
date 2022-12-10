package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class BlazingArrowsPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.BLAZE_POWDER);

    public BlazingArrowsPerk() {
        super("Blazing Arrows", PerkRarity.RARE,
                Arrays.asList("&715% chance of shooting a fire",
                        "&7arrow with a bow."));
    }
}
