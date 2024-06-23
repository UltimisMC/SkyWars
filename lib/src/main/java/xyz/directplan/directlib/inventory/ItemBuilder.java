package xyz.directplan.directlib.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import xyz.directplan.directlib.PluginUtility;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private final ItemStack is;

    public ItemBuilder(Material mat) { this.is = new ItemStack(mat); }

    public ItemBuilder(ItemStack is) { this.is = is; }

    public ItemBuilder amount(int amount) {
        this.is.setAmount(amount);
        return this;
    }

    public ItemBuilder name(String name) {
        ItemMeta meta = this.is.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        this.is.setItemMeta(meta);
        return this;
    }

    public ItemBuilder lore(String name) {
        ItemMeta meta = this.is.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        lore.add(PluginUtility.translateMessage(name));
        meta.setLore(lore);
        this.is.setItemMeta(meta);
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        List<String> toSet = new ArrayList<String>();
        ItemMeta meta = this.is.getItemMeta();
        for (String string : lore) {
            toSet.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        meta.setLore(toSet);
        this.is.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setupHead(boolean ranked){
        if(!ranked){
            this.is.setType(Material.SKULL_ITEM);
            durability(3);
        }
        return this;
    }

    public ItemBuilder durability(int durability) {
        this.is.setDurability((short)durability);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder data(int data) {
        this.is.setData(new MaterialData(this.is.getType(), (byte)data));
        return this;
    }

    public ItemBuilder enchantment(Enchantment enchantment, int level) {
        this.is.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder enchantment(Enchantment enchantment) {
        return enchantment(enchantment, 1);
    }

    public ItemBuilder type(Material material) {
        this.is.setType(material);
        return this;
    }
    public ItemBuilder skullOwner(String name) {
        ItemMeta meta = this.is.getItemMeta();
        SkullMeta skull = (SkullMeta)meta;
        skull.setOwner(name);
        this.is.setItemMeta(skull);
        return this;
    }
    public ItemBuilder clearLore() {
        ItemMeta meta = this.is.getItemMeta();
        meta.setLore(new ArrayList<>());
        this.is.setItemMeta(meta);
        return this;
    }

    public ItemBuilder clearEnchantments() {
        for (Enchantment e : this.is.getEnchantments().keySet()) {
            this.is.removeEnchantment(e);
        }
        return this;
    }

    public ItemStack build() { return this.is; }
}
