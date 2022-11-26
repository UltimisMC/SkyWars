package com.ultimismc.skywars.core.game.features;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class PurchasableDesign {

    private final Material material;
    private final int durability;

    @Setter private String texture;

    public PurchasableDesign(Material material) {
        this(material, 0);
    }

    public PurchasableDesign(String texture) {
        this(Material.SKULL_ITEM, 3);
    }
}
