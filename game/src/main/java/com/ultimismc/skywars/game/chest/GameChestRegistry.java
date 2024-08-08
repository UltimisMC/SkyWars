package com.ultimismc.skywars.game.chest;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.ItemEnchantment;

import java.util.*;

/**
 * @author DirectPlan
 */
public abstract class GameChestRegistry {


    private final Map<ChestItemCategory, List<ChestItem>> chestItems = new HashMap<>();
    private final Map<ChestItemCategory, List<ChestItem>> middleChestItems = new HashMap<>();

    public abstract void buildItems();

    public void refillChest(RefillPhase refillPhase, Chest chest) {

        List<ChestItem> items = new ArrayList<>();

        // Minimum and maximum chest items for the refill.
        int minimumChestItems = refillPhase.getMinimumChestItems();
        int maximumChestItems = refillPhase.getMaximumChestItems();

        // Get a random items size based on the minimum and maximum items size from the refill phase
        int maximumItemsSize = PluginUtility.getRandomInteger(minimumChestItems, maximumChestItems);

        Map<ChestItemCategory, List<ChestItem>> chestMap = (chest.isMidChest() ? middleChestItems : chestItems);

        for(ChestItemCategory itemCategory : ChestItemCategory.values()) {
            int categoryPercentage = itemCategory.getPercentage();
            if(!PluginUtility.hasChanceOccurred(categoryPercentage)) continue;

            int maximumCategoryItems = itemCategory.getMaximumItems();
            int categoryLength = PluginUtility.getRandomInteger(1, maximumCategoryItems);

            List<ChestItem> toWrap = chestMap.get(itemCategory);
            if(toWrap == null) continue;
            List<ChestItem> itemCollection = new ArrayList<>(toWrap);
            Collections.shuffle(itemCollection);

            int itemCount = 0;
            for(ChestItem chestItem : itemCollection) {
                // Checking if the items exceeded the declared random items size.

                if(itemCount >= categoryLength) break;

                // Checking if the item's percentage chance has occurred.
                if(!PluginUtility.hasChanceOccurred(categoryPercentage)) continue;

                items.add(chestItem);
                itemCount++;
            }
            if(items.size() >= maximumItemsSize) break;
        }

//        List<ChestItem> items = new ArrayList<>((chest.isMidChest() ? middleChestItems : chestItems));

        /*
        ^^^ Order this list from the highest percentage to the lowest
        So that when you try to set the slots, the 100% ones
        will be placed first, so they don't get overridden.
         */

        if(refillPhase.hasEnderpearl()) {
            items.add(new ChestItem(Material.ENDER_PEARL));
        }
        if(refillPhase.hasCompass()) {
            // Add code to set direction of a "random" player.
            items.add(new ChestItem(Material.COMPASS));
        }

        // Clear inventory
        Inventory chestInventory = chest.getInventory();
        chestInventory.clear();

        int inventorySize = chestInventory.getSize();

        // Iterate through the items
        for(ChestItem chestItem : items) {
            ItemStack itemStack = chestItem.getItemStack();
            // Get a random slot in the inventory
            int randomSlot = PluginUtility.getRandomInteger(0, inventorySize);

            // Check if the declared random slot already has an item
            // If there is an existing item at that slot, do a recursive loop until you find an empty slot.
            ItemStack previousItem = chestInventory.getItem(randomSlot);
            while (previousItem != null && previousItem.getType() != Material.AIR) {
                // Slot is not empty, finding an empty one.
                randomSlot = PluginUtility.getRandomInteger(0, inventorySize);
                previousItem = chestInventory.getItem(randomSlot);
            }
            // The item wasn't null, and it wasn't air! ^^
            // Add the item to the chest inventory in the empty slot.
            chestInventory.setItem(randomSlot, itemStack);
        }
    }

    // I left the percentage parameter unused just in case I might need it later.
    public void addChestItem(ChestItemCategory category, boolean middle, int percentage, Material material, int durability, int amount, ItemEnchantment... enchantments) {
        ChestItem chestItem = new ChestItem(material, amount);
        chestItem.setDurability(durability);
        if(enchantments != null) {
            chestItem.addEnchantments(enchantments);
        }
        Map<ChestItemCategory, List<ChestItem>> chestMap = (middle ? middleChestItems : chestItems);
        List<ChestItem> chestItems = chestMap.computeIfAbsent(category, s -> new ArrayList<>());
        chestItems.add(chestItem);
    }

    public void addChestItem(ChestItemCategory category, boolean middle, int percentage, Material material, int amount, ItemEnchantment... enchantments) {
        addChestItem(category, middle, percentage, material, 0, amount, enchantments);
    }

    public void addChestItem(ChestItemCategory category, boolean middle, int percentage, Material material, ItemEnchantment... enchantments) {
        addChestItem(category, middle, percentage, material, 1, enchantments);
    }

    public void addChestItem(ChestItemCategory category, boolean middle, int percentage, Material material) {
        addChestItem(category, middle, percentage, material, 1);
    }

    public int getSize() {
        return middleChestItems.size() + chestItems.size();
    }
}
