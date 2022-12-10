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
public class RobberyPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_BARDING);

    public RobberyPerk() {
        super("Barbarian", PerkRarity.LEGENDARY,
                Arrays.asList("&720% chance to drop a player's",
                        "&7held item by hitting them with",
                        "&7your fist."));
    }

    // %zaml-displayname% &emade you drop your item with &bRobbery Perk
}
