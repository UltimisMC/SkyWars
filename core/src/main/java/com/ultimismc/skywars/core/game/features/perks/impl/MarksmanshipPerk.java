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
public class MarksmanshipPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.BOW);

    public MarksmanshipPerk() {
        super("Marksmanship", PerkRarity.LEGENDARY,
                Arrays.asList("&7After getting 2 kills with a",
                        "&7bow, all of your bows will get",
                        "&7enchanted with Power I."));
    }
}
