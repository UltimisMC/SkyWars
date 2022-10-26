package com.ultimismc.skywars.core.game.features.kits;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;

import java.util.List;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class KitItem {

    private final Material material;
    private final short durability;
    private final String displayName;
    private final List<String> lore;
    private final int amount;

    @Setter private ItemAbility ability;


    public KitItem(Material material, String displayName, List<String> lore, int amount) {
        this(material, (short) 0, displayName, lore, amount);
    }

    public KitItem(Material material, String displayName, List<String> lore) {
        this(material, displayName, lore, 1);
    }

    public KitItem(Material material, String displayName) {
        this(material, displayName, null);
    }
}
