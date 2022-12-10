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
public class FatPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.GOLDEN_APPLE);

    public FatPerk() {
        super("Fat", PerkRarity.RARE,
                Arrays.asList("&7Gain 20s of absorption I when",
                        "&7the game starts."));
    }
}
