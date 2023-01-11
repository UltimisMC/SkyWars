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

    private final MenuItem menuItem;

    public ChestItem(Material material, int amount) {
        menuItem = new MenuItem(material, null);
        menuItem.setAmount(amount);
    }

    public ChestItem(Material material) {
        this(material, 1);
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
