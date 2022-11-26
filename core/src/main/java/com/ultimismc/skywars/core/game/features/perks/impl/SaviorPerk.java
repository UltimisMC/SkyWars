package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class SaviorPerk extends AbstractPerk {

    private final PurchasableDesign design = new PurchasableDesign(Material.APPLE);

    public SaviorPerk() {
        super("Savior", PerkRarity.COMMON,
                Arrays.asList("&7Enemy kills give you absorption I",
                        "&7for 7 seconds."));
    }
}
