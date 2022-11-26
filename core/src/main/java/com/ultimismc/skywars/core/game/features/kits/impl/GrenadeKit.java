package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class GrenadeKit extends AbstractKit {

    private final PurchasableDesign design = new PurchasableDesign(Material.MONSTER_EGG, 50);

    public GrenadeKit() {
        super("Grenade", KitRarity.COMMON);
    }
}
