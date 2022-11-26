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
public class RevengePerk extends AbstractPerk {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_SWORD);

    public RevengePerk() {
        super("Revenge", PerkRarity.LEGENDARY,
                "&7Spawn a spider when you die.");
    }
}
