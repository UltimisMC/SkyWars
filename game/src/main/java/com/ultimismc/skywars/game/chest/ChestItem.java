package com.ultimismc.skywars.game.chest;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.directplan.directlib.inventory.ItemEnchantment;
import xyz.directplan.directlib.inventory.MenuItem;

/**
 * @author DirectPlan
 */
@Getter
public class ChestItem {

    private final int percentage;
    private final MenuItem menuItem;

    public ChestItem(int percentage, Material material, int amount) {
        this.percentage = percentage;
        menuItem = new MenuItem(material, null);
        menuItem.setAmount(amount);
    }

    public ChestItem(int percentage, Material material) {
        this(percentage, material, 1);
    }

    public void addEnchantments(ItemEnchantment... enchantment) {
        menuItem.addEnchantments(enchantment);
    }

    public void setDurability(int durability) {
        menuItem.setDurability(durability);
    }

    public int getItemAmount() {
        return menuItem.getItemAmount();
    }

    public ItemStack getItemStack() {
        return menuItem.getItemStack();
    }
}
