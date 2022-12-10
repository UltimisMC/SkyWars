package com.ultimismc.skywars.core.game.features.kits;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.ItemEnchantment;
import xyz.directplan.directlib.inventory.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public class KitItem {

    private final MenuItem menuItem;
    private final List<ItemEnchantment> enchantments = new ArrayList<>();

    private ItemAbility itemAbility;

    public KitItem(Material material, int durability) {
        menuItem = new MenuItem(material, null, durability);
    }

    public KitItem(Material material) {
        this(material, 0);
    }

    public ItemStack getItemStack() {
        return menuItem.getItemStack();
    }
    public String getDisplayName() {
        String displayName = menuItem.getDisplayName();
        if(displayName == null) {
            ItemStack itemStack = getItemStack();
            displayName = PluginUtility.getItemNativeName(itemStack);
        }
        return displayName;
    }

    public int getAmount() {
        return menuItem.getItemAmount();
    }

    public List<ItemEnchantment> getEnchantments() {
        return enchantments;
    }

    public KitItem displayName(String displayName) {
        menuItem.setDisplayName(displayName);
        return this;
    }

    public KitItem amount(int amount) {
        menuItem.setAmount(amount);
        return this;
    }

    public KitItem itemEnchantment(ItemEnchantment enchantment) {
        menuItem.addEnchantments(enchantment);
        enchantments.add(enchantment);
        return this;
    }

    public KitItem itemEnchantment(Enchantment enchantment, String name, int level) {
        return itemEnchantment(new ItemEnchantment(enchantment, name, level));
    }

    public KitItem itemAbility(ItemAbility ability) {
        this.itemAbility = ability;
        return this;
    }

/*

    public void addItemEnchantment(ItemEnchantment enchantment) {
        enchantments.add(enchantment);
    }

    public void addItemEnchantment(Enchantment enchantment, String name, int level) {
        addItemEnchantment(new ItemEnchantment(enchantment, name, level));
    }
 */
}
