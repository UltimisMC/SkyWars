package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class MarksmanshipPerk extends AbstractPerk {

    public MarksmanshipPerk() {
        super(Material.BOW, "Marksmanship", PerkRarity.LEGENDARY,
                Arrays.asList("&7After getting 2 kills with a",
                        "&7bow, all of your bows will get",
                        "&7enchanted with Power I."));
    }
}
