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
public class JuggernautPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.DIAMOND_HELMET);

    public JuggernautPerk() {
        super("Juggernaut", PerkRarity.RARE,
                Arrays.asList("&7Enemy kills give you regen I for",
                        "&710 seconds."));
    }
}
