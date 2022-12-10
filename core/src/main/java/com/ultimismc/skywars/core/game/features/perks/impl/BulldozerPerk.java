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
public class BulldozerPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.ANVIL);

    public BulldozerPerk() {
        super("Bulldozer", PerkRarity.LEGENDARY,
                Arrays.asList("&7Enemy kills give you strength I",
                        "&7for 5s in Solo (2s in Team Mode)."));
    }
}
