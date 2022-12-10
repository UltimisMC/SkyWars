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
public class ArrowRecoveryPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.ARROW);

    public ArrowRecoveryPerk() {
        super("Arrow Recovery", PerkRarity.RARE,
                Arrays.asList("&750% chance of getting your arrow",
                        "&7back on bow hit."));
    }
}
