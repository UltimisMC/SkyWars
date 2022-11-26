package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class KnowledgePerk extends AbstractPerk {

    private final PurchasableDesign design = new PurchasableDesign(Material.BOOK);

    public KnowledgePerk() {
        super("Knowledge", PerkRarity.COMMON,
                "&7Every kill you gain 3 EXP Level.");
    }
}
