package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class EnchanterKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.ENCHANTMENT_TABLE);

    public EnchanterKit() {
        super("Enchanter", KitRarity.RARE);
    }
}
