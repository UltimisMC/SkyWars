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
public class AnnoyOMitePerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.MONSTER_EGG, 60);

    public AnnoyOMitePerk() {
        super("Annoy-o-mite", PerkRarity.RARE,
                Arrays.asList("&710% chance to spawn SilverFish",
                        "&7next to enemies when you hit",
                        "&7them with a bow."));
    }
}
