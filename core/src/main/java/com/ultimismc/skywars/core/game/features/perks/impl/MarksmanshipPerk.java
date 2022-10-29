package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public class MarksmanshipPerk extends AbstractPerk {

    public MarksmanshipPerk() {
        super(Material.BOW, "Marksmanship", PerkRarity.LEGENDARY,
                "After getting 2 kills with a bow, all of your bows get enchanted with Power I");
    }
}
