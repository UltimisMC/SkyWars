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
public class EnvironmentalExpertPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.LEATHER_CHESTPLATE);

    public EnvironmentalExpertPerk() {
        super("Environmental Expert", PerkRarity.RARE,
                Arrays.asList("&7Reduces environmental damage by",
                        "&750%"));
    }
}
