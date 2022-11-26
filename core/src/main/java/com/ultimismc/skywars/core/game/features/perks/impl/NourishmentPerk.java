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
public class NourishmentPerk extends AbstractPerk {

    private final PurchasableDesign design = new PurchasableDesign(Material.BREAD);

    public NourishmentPerk() {
        super("Nourishment", PerkRarity.COMMON,
                Arrays.asList("&7Every kill gives you full hunger",
                        "&7and saturation."));
    }
}
