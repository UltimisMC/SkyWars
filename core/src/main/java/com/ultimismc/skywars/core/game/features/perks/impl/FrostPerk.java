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
public class FrostPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.ICE);

    public FrostPerk() {
        super("Frost", PerkRarity.LEGENDARY,
                Arrays.asList("&740% chance to give a player",
                        "&7slowness I for 3 seconds on",
                        "&7fully charged bow hit."));
    }
}
