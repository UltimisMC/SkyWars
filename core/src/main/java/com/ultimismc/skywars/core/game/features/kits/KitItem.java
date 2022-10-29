package com.ultimismc.skywars.core.game.features.kits;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import xyz.directplan.directlib.inventory.ItemEnchantment;

import java.util.ArrayList;
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

    private final List<ItemEnchantment> enchantments = new ArrayList<>();

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

    public void addItemEnchantment(ItemEnchantment enchantment) {
        enchantments.add(enchantment);
    }

    public void addItemEnchantment(Enchantment enchantment, String name, int level) {
        addItemEnchantment(new ItemEnchantment(enchantment, name, level));
    }
}
