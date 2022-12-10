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
public class ResistanceBoostPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_CHESTPLATE);

    public ResistanceBoostPerk() {
        super("Resistance Boost", PerkRarity.COMMON,
                Arrays.asList("&7Gain 15s of resistance II when",
                        "&7the game starts."));
    }
}
