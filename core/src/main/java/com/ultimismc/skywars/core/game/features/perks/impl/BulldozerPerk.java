package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class BulldozerPerk extends AbstractPerk {

    public BulldozerPerk() {
        super(Material.ANVIL, "Bulldozer", PerkRarity.LEGENDARY,
                Arrays.asList("&7Enemy kills give you strength I",
                        "&7for 5s in Solo (2s in Team Mode)."));
    }
}
