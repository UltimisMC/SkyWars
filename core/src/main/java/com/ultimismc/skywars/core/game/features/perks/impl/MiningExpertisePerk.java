package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class MiningExpertisePerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_PICKAXE);

    public MiningExpertisePerk() {
        super("Mining Expertise", PerkRarity.COMMON,
                "&7Get 1 extra ore per block mined.");
    }
}
