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
public class NecromancerPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.ROTTEN_FLESH);

    public NecromancerPerk() {
        super("Necromancer", PerkRarity.LEGENDARY,
                Arrays.asList("&716% chance to spawn a friendly",
                        "&7Zombie on kill."));
    }
}
