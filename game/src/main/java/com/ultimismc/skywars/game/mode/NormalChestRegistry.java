package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.game.chest.ChestItemCategory;
import com.ultimismc.skywars.game.chest.GameChestRegistry;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import xyz.directplan.directlib.inventory.ItemEnchantment;

/**
 * @author DirectPlan
 */
public class NormalChestRegistry extends GameChestRegistry {

    @Override
    public void buildItems() {

        // Non-middle Items
        addChestItem(ChestItemCategory.ARMOR, false, 80, Material.IRON_HELMET);
        addChestItem(ChestItemCategory.ARMOR, false, 60, Material.IRON_CHESTPLATE);
        addChestItem(ChestItemCategory.ARMOR, false, 70, Material.IRON_LEGGINGS);
        addChestItem(ChestItemCategory.ARMOR, false, 80, Material.IRON_BOOTS);

        addChestItem(ChestItemCategory.ARMOR, false, 60, Material.GOLD_CHESTPLATE);
        addChestItem(ChestItemCategory.ARMOR, false, 70, Material.GOLD_LEGGINGS);
        addChestItem(ChestItemCategory.ARMOR, false, 50, Material.CHAINMAIL_LEGGINGS);

        addChestItem(ChestItemCategory.ARMOR, false, 90, Material.LEATHER_HELMET);
        addChestItem(ChestItemCategory.ARMOR, false, 70, Material.LEATHER_CHESTPLATE);
        addChestItem(ChestItemCategory.ARMOR, false, 80, Material.LEATHER_LEGGINGS);
        addChestItem(ChestItemCategory.ARMOR, false, 90, Material.LEATHER_BOOTS);

        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.STONE_SWORD);
        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.STONE_SWORD, new ItemEnchantment(Enchantment.DAMAGE_ALL, 1));
        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.IRON_SWORD);
        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.BOW);
        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.ARROW, 10);

        addChestItem(ChestItemCategory.TOOLS, false, 80, Material.STONE_PICKAXE);
        addChestItem(ChestItemCategory.TOOLS, false, 60, Material.STONE_AXE);
        addChestItem(ChestItemCategory.TOOLS, false, 80, Material.IRON_PICKAXE);
        addChestItem(ChestItemCategory.TOOLS, false, 80, Material.IRON_AXE);


        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 60, Material.FISHING_ROD);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 60, Material.WATER_BUCKET);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 40, Material.LAVA_BUCKET);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 40, Material.SNOW_BALL, 8);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 60, Material.SNOW_BALL, 12);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 20, Material.SNOW_BALL, 16);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 60, Material.EGG, 8);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 40, Material.EGG, 12);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 20, Material.EGG, 16);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 60, Material.EXP_BOTTLE, 8);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 40, Material.EXP_BOTTLE, 12);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 20, Material.EXP_BOTTLE, 16);
        addChestItem(ChestItemCategory.FOOD, false, 60, Material.COOKED_BEEF, 8); // FOOD
        addChestItem(ChestItemCategory.FOOD, false, 40, Material.COOKED_BEEF, 12); // FOOD
        addChestItem(ChestItemCategory.FOOD, false, 20, Material.COOKED_BEEF, 16); // FOOD
        addChestItem(ChestItemCategory.FOOD, false, 50, Material.GOLDEN_APPLE, 1); // FOOD
        addChestItem(ChestItemCategory.FOOD, false, 40, Material.GOLDEN_APPLE, 2);
        addChestItem(ChestItemCategory.BLOCKS, false, 90, Material.STONE, 16); // BLOCKS
        addChestItem(ChestItemCategory.BLOCKS, false, 70, Material.STONE, 24);
        addChestItem(ChestItemCategory.BLOCKS, false, 50, Material.STONE, 32);
        addChestItem(ChestItemCategory.BLOCKS, false, 30, Material.STONE, 48);
        addChestItem(ChestItemCategory.BLOCKS, false, 90, Material.WOOD, 16);
        addChestItem(ChestItemCategory.BLOCKS, false, 70, Material.WOOD, 24);
        addChestItem(ChestItemCategory.BLOCKS, false, 50, Material.WOOD, 32);

        // Middle Items

        addChestItem(ChestItemCategory.ARMOR, true, 100, Material.DIAMOND_HELMET, new ItemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
        addChestItem(ChestItemCategory.ARMOR, true, 100, Material.IRON_CHESTPLATE, new ItemEnchantment(Enchantment.PROTECTION_PROJECTILE, 3));
        addChestItem(ChestItemCategory.ARMOR, true, 100, Material.IRON_LEGGINGS, new ItemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4));
        addChestItem(ChestItemCategory.ARMOR, true, 100, Material.DIAMOND_BOOTS, new ItemEnchantment(Enchantment.PROTECTION_FIRE, 5));

        addChestItem(ChestItemCategory.WEAPON, true, 100, Material.IRON_SWORD, new ItemEnchantment(Enchantment.FIRE_ASPECT, 1));
        addChestItem(ChestItemCategory.WEAPON, true, 100, Material.BOW, new ItemEnchantment(Enchantment.ARROW_DAMAGE, 1));

        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.ENDER_PEARL);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.TNT, 10);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.SNOW_BALL, 64);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.LAVA_BUCKET);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.EXP_BOTTLE, 64);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.FISHING_ROD, new ItemEnchantment(Enchantment.KNOCKBACK, 2));
        addChestItem(ChestItemCategory.TOOLS, true, 100, Material.DIAMOND_PICKAXE, new ItemEnchantment(Enchantment.DIG_SPEED, 1));
        addChestItem(ChestItemCategory.TOOLS, true, 100, Material.DIAMOND_AXE, new ItemEnchantment(Enchantment.DIG_SPEED, 1));
        addChestItem(ChestItemCategory.FOOD, true, 100, Material.GOLDEN_APPLE);
        addChestItem(ChestItemCategory.BLOCKS, true, 100, Material.LOG, 64);


    }
}
