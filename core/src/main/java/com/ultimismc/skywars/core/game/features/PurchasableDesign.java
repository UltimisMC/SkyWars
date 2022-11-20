package com.ultimismc.skywars.core.game.features;

import lombok.Data;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Data
public class PurchasableDesign {

    private final Material material;
    private final int durability;
}
