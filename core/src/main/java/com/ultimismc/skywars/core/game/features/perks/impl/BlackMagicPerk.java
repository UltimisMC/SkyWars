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
public class BlackMagicPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.CAULDRON_ITEM);

    public BlackMagicPerk() {
        super("Black Magic", PerkRarity.LEGENDARY,
                Arrays.asList("&730% chance to get an enderpearl",
                        "&7after throwing a player in the",
                        "&7void."));
    }
}
