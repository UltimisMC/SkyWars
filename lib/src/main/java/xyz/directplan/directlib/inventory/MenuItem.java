package xyz.directplan.directlib.inventory;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import xyz.directplan.directlib.ItemBuilder;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author DirectPlan
 */
@Getter
public class MenuItem implements Cloneable {

    private ItemStack itemStack;
    private final ItemBuilder builder;
    private final String displayName;
    @Setter private Object itemKey;
    @Setter private boolean cancelAction = true;
    @Setter private int slot = -1;
    private int itemAmount = 1;

    @Setter private ActionableItem action;

    public MenuItem(Material type, String displayName) {
        this(type, displayName, 0);
    }

    public MenuItem(Material type, String displayName, int durability){
        this(type, displayName, durability, null);
    }

    public MenuItem(Material type, String displayName, ActionableItem action){
        this(type, displayName, 0, action);
    }

    public MenuItem(Material type, String displayName, int durability, ActionableItem action){
        this.builder = new ItemBuilder(type);
        if(displayName != null) {
            builder.name(displayName);
        }
        if(durability > 0){
            this.builder.durability(durability);
        }
        this.itemStack = builder.build();
        this.displayName = displayName;
        this.action = action;
    }

    public void setCustomSkullName(String name) {
        this.itemStack = builder.type(Material.SKULL_ITEM).durability(3).skullOwner(name).build();
    }
    public void setCustomSkullProperty(String value) {
        if(value == null) return;
        ItemStack item = builder.type(Material.SKULL_ITEM).durability(3).build();

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures", new Property("textures", value));

        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        Field profileField;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, gameProfile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
        itemStack.setItemMeta(skullMeta);

        this.itemStack = item;
    }

    public void addEnchantments(ItemEnchantment... enchantments) {
        for(ItemEnchantment enchantment : enchantments) {
            builder.enchantment(enchantment.getEnchantment(), enchantment.getLevel());
        }
        itemStack = builder.build();
    }

    public void addFlags(ItemFlag... flags) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(flags);
        itemStack.setItemMeta(meta);
    }

    public void addInvisibleEnchantment() {
        addEnchantments(new ItemEnchantment(Enchantment.DIG_SPEED, 1));
        addFlags(ItemFlag.HIDE_ENCHANTS);
    }

    public Material getType() {
        return itemStack.getType();
    }

    public List<String> getLore() {
        return itemStack.getItemMeta().getLore();
    }

    public void setDurability(int durability) {
        itemStack = builder.durability(durability).build();
    }

    public void setAmount(int amount) {
        this.itemAmount = amount;
        itemStack = builder.amount(amount).build();
    }

    public void markUnbreakable() {
        ItemMeta meta = itemStack.getItemMeta();
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemStack.setItemMeta(meta);
    }

    public void color(Color color) {
        this.itemStack = builder.color(color).build();
    }

    public void setLore(List<String> lore){
        this.itemStack = builder.lore(lore).build();
    }

    public void setLore(String lore) {
        this.itemStack = builder.lore(lore).build();
    }

    public void setDisplayName(String displayName) {
        this.itemStack = builder.name(displayName).build();
    }

    public boolean hasAction(){
        return action != null;
    }

    public void performAction(MenuItem item, Player clicker, Block clickedBlock, ClickType clickType){
        if(this.action != null){
            this.action.performAction(item, clicker, clickedBlock, clickType);
        }
    }

    public void performAction(MenuItem item, Player clicker, ClickType clickType){
        performAction(item, clicker, null, clickType);
    }

    public boolean updateButtons(Player clicker, ClickType clickType) {
        return (action != null && action.updateButtons(clicker, clickType));
    }

    public void removeCompoundKey(String compoundKey) {
        net.minecraft.server.v1_8_R3.ItemStack raw = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = raw.getTag();
        tagCompound.remove(compoundKey);
        raw.setTag(tagCompound);
        itemStack = CraftItemStack.asBukkitCopy(raw);
    }

    public void setCompoundKey(List<String> keys, List<String> values) {
        net.minecraft.server.v1_8_R3.ItemStack raw = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = raw.getTag();
        int keyLength = keys.size();
        if(keyLength != values.size()) throw new IllegalStateException("Keys size doesn't match the values size");
        for(int i = 0; i < keyLength; i++) {
            String key = keys.get(i);
            String value = values.get(i);
            tagCompound.setString(key, value);
        }

        raw.setTag(tagCompound);
        itemStack = CraftItemStack.asBukkitCopy(raw);
    }

    public void setCompoundKeys(List<String> compoundKeys) {
        setCompoundKey(compoundKeys, compoundKeys);
    }
    public void setCompoundKey(String compoundKey) {
        setCompoundKeys(Collections.singletonList(compoundKey));
    }

    @Override
    public MenuItem clone() {
        try {
            MenuItem clone = (MenuItem) super.clone();
            clone.setCancelAction(true);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
