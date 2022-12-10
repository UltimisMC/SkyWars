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
public class BridgerPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.WOOD);

    public BridgerPerk() {
        super("Bridger", PerkRarity.COMMON,
                Arrays.asList("&7Grants 50% chance to not consume",
                        "&7placeable blocks."));
    }
}
