package xyz.directplan.directlib.inventory;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
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
import xyz.directplan.directlib.StringUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
@Getter
public class MenuItem implements Cloneable {

    private static final Map<Material, String> MATERIAL_NAME_MAP = new HashMap<>();

    static {
        for(Material material : Material.values()) {
            String name = material.name().toLowerCase();
            name = StringUtil.fixName(name);

            MATERIAL_NAME_MAP.put(material, name);
        }
    }

    private ItemStack itemStack;
    private ItemBuilder builder;
    private final String displayName;
    @Setter private Object itemKey;
    @Setter private boolean cancelAction = true;
    @Setter private int slot = -1;
    private int itemAmount = 1;

    @Setter private ActionableItem action;
    @Setter private InventoryUI openInventory;

    public MenuItem(Material type) {
        this(type, null);
    }

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
        if(displayName != null) builder.name(displayName);
        if(durability > 0) builder.durability(durability);

        this.itemStack = builder.build();
        this.displayName = itemStack.getItemMeta().getDisplayName();
        this.action = action;
        this.itemStack.setAmount(1);
    }

    public Material getType() {
        return itemStack.getType();
    }

    public void setDurability(int durability) {
        itemStack.setDurability((short) durability);
    }

    public void setCustomSkullName(String name) {
        this.itemStack = builder.type(Material.SKULL_ITEM).durability(3).skullOwner(name).build();
    }

    public void setCustomSkullProperty(String value) {
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

    public List<String> getLore() {
        return itemStack.getItemMeta().getLore();
    }

    public void setAmount(int amount) {
        this.itemAmount = amount;
        itemStack = builder.amount(amount).build();
    }
    public void markUnbreakable() {
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemStack.setItemMeta(meta);
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

    public boolean hasOpenInventory() {
        return openInventory != null;
    }

    public void performAction(MenuItem item, Player clicker, Block clickedBlock, ClickType clickType){
        if(action == null) return;

        action.performAction(item, clicker, clickedBlock, clickType);
    }

    public void performAction(MenuItem item, Player clicker, ClickType clickType){
        performAction(item, clicker, null, clickType);
    }

    public boolean isRefreshable(Player clicker, ClickType clickType) {
        return (action != null && action.isRefreshable(clicker, clickType));
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.builder = new ItemBuilder(itemStack);
    }


    public void setCompoundKey(String compoundKey) {
        net.minecraft.server.v1_8_R3.ItemStack raw = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = raw.getTag();
        tagCompound.setString(compoundKey, compoundKey);
        raw.setTag(tagCompound);
        itemStack = CraftItemStack.asBukkitCopy(raw);
    }

    public void setCompoundKey(List<String> compoundKeys) {
        compoundKeys.forEach(this::setCompoundKey);
    }

    public static String getMaterialName(Material material) {
        return MATERIAL_NAME_MAP.get(material);
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
