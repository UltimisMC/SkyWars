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
public class LuckyCharmPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.SPECKLED_MELON);

    public LuckyCharmPerk() {
        super("Lucky Charm", PerkRarity.COMMON,
                Arrays.asList("&730% chance to get a Golden Apple",
                                "&7after a kill."));
    }
}
