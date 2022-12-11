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

    private final Set<ChestItem> chestItems = new HashSet<>();
    private final Set<ChestItem> middleChestItems = new HashSet<>();

    public abstract void buildItems();

    public void refillChest(RefillPhase refillPhase, Chest chest) {
        List<ChestItem> items = new ArrayList<>((chest.isMidChest() ? middleChestItems : chestItems));

        /*
        ^^^ Order this list from the highest percentage to the lowest
        So that when you try to set the slots, the 100% ones
        will be placed first, so they don't get overridden.
         */

        if(refillPhase.hasEnderpearl()) {
            items.add(new ChestItem(100, Material.ENDER_PEARL));
        }
        if(refillPhase.hasCompass()) {
            // Some code here to get direction of a random player.
            items.add(new ChestItem(100, Material.COMPASS));
        }
        Collections.shuffle(items);

        int minimumChestItems = refillPhase.getMinimumChestItems();
        int maximumChestItems = refillPhase.getMaximumChestItems();

        int randomItemsLength = PluginUtility.getRandomInteger(minimumChestItems, maximumChestItems);

        Inventory chestInventory = chest.getInventory();
        chestInventory.clear();

        int inventorySize = chestInventory.getSize();

        int itemCount = 0;
        for(ChestItem chestItem : items) {
            if(itemCount >= randomItemsLength) break;
            int percentage = chestItem.getPercentage();
            ItemStack itemStack = chestItem.getItemStack();

            if(!PluginUtility.hasChanceOccurred(percentage)) continue;
            itemCount++;

            int randomSlot = PluginUtility.getRandomInteger(0, inventorySize);

            ItemStack previousItem = chestInventory.getItem(randomSlot);
            while (previousItem != null && previousItem.getType() != Material.AIR) {
                // Slot is not empty, finding an empty one.
                randomSlot = PluginUtility.getRandomInteger(0, inventorySize);
                previousItem = chestInventory.getItem(randomSlot);
            }
            chestInventory.setItem(randomSlot, itemStack);
        }
    }

    public void addChestItem(boolean middle, int percentage, Material material, int durability, int amount, ItemEnchantment... enchantments) {
        ChestItem chestItem = new ChestItem(percentage, material, amount);
        chestItem.setDurability(durability);
        if(enchantments != null) {
            chestItem.addEnchantments(enchantments);
        }
        if(middle) {
            middleChestItems.add(chestItem);
            return;
        }
        chestItems.add(chestItem);
    }

    public void addChestItem(boolean middle, int percentage, Material material, int amount, ItemEnchantment... enchantments) {
        addChestItem(middle, percentage, material, 0, amount, enchantments);
    }

    public void addChestItem(boolean middle, int percentage, Material material, ItemEnchantment... enchantments) {
        addChestItem(middle, percentage, material, 1, enchantments);
    }

    public void addChestItem(boolean middle, int percentage, Material material) {
        addChestItem(middle, percentage, material, 1);
    }

    public int getSize() {
        return middleChestItems.size() + chestItems.size();
    }
}
