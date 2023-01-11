package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.game.chest.ChestItemCategory;
import com.ultimismc.skywars.game.chest.GameChestRegistry;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import xyz.directplan.directlib.inventory.ItemEnchantment;

/**
 * @author DirectPlan
 */
public class InsaneChestRegistry extends GameChestRegistry {

    @Override
    public void buildItems() {

        // Non-middle Chest Items
        addChestItem(ChestItemCategory.ARMOR, false, 100, Material.DIAMOND_HELMET);
        addChestItem(ChestItemCategory.ARMOR, false, 100, Material.DIAMOND_CHESTPLATE);
        addChestItem(ChestItemCategory.ARMOR, false, 100, Material.DIAMOND_LEGGINGS);
        addChestItem(ChestItemCategory.ARMOR, false, 100, Material.DIAMOND_BOOTS);

        addChestItem(ChestItemCategory.ARMOR, false, 100, Material.IRON_HELMET);
        addChestItem(ChestItemCategory.ARMOR, false, 100, Material.IRON_CHESTPLATE);
        addChestItem(ChestItemCategory.ARMOR, false, 100, Material.IRON_LEGGINGS);
        addChestItem(ChestItemCategory.ARMOR, false, 100, Material.IRON_BOOTS);

        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.STONE_SWORD, new ItemEnchantment(Enchantment.DAMAGE_ALL, 1));
        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.DIAMOND_SWORD);
        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.DIAMOND_SWORD, new ItemEnchantment(Enchantment.DAMAGE_ALL, 1));
        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.BOW, new ItemEnchantment(Enchantment.ARROW_DAMAGE, 1));
        addChestItem(ChestItemCategory.WEAPON, false, 100, Material.BOW, new ItemEnchantment(Enchantment.ARROW_DAMAGE, 3));
        addChestItem(ChestItemCategory.POTIONS, false, 100, Material.POTION, 16385, 1); // Regeneration I
        addChestItem(ChestItemCategory.POTIONS, false, 100, Material.POTION, 16417, 1); // Regeneration II
        addChestItem(ChestItemCategory.POTIONS, false, 100, Material.POTION, 16420, 1); // Poison I
        addChestItem(ChestItemCategory.POTIONS, false, 100, Material.POTION, 16418, 1); // Speed II

        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 100, Material.EGG, 16);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 100, Material.SNOW_BALL, 16);
        addChestItem(ChestItemCategory.POTIONS, false, 100, Material.POTION, 8227, 1); // Drinkable Resistance Potion
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 100, Material.FISHING_ROD);
        addChestItem(ChestItemCategory.FOOD, false, 100, Material.COOKED_BEEF, 16);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 100, Material.EXP_BOTTLE, 32);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 100, Material.EXP_BOTTLE, 64);
        addChestItem(ChestItemCategory.TOOLS, false, 100, Material.DIAMOND_AXE);
        addChestItem(ChestItemCategory.TOOLS, false, 100, Material.DIAMOND_PICKAXE);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 100, Material.WATER_BUCKET);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 100, Material.LAVA_BUCKET);
        addChestItem(ChestItemCategory.MISCELLANEOUS, false, 100, Material.ENCHANTMENT_TABLE);
        addChestItem(ChestItemCategory.BLOCKS, false, 100, Material.LOG, 64);
        addChestItem(ChestItemCategory.BLOCKS, false, 100, Material.WOOD, 16);
        addChestItem(ChestItemCategory.BLOCKS, false, 100, Material.WOOD, 32);
        addChestItem(ChestItemCategory.BLOCKS, false, 100, Material.WOOD, 64);
        addChestItem(ChestItemCategory.BLOCKS, false, 100, Material.STONE, 16);
        addChestItem(ChestItemCategory.BLOCKS, false, 100, Material.STONE, 32);
        addChestItem(ChestItemCategory.BLOCKS, false, 100, Material.STONE, 64);

        // Middle Chest Items
        // Armor Pieces
        addChestItem(ChestItemCategory.ARMOR, true, 100, Material.DIAMOND_HELMET, new ItemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4));
        addChestItem(ChestItemCategory.ARMOR, true, 100, Material.DIAMOND_CHESTPLATE, new ItemEnchantment(Enchantment.PROTECTION_PROJECTILE, 3));
        addChestItem(ChestItemCategory.ARMOR, true, 100, Material.DIAMOND_LEGGINGS, new ItemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4));
        addChestItem(ChestItemCategory.ARMOR, true, 100, Material.DIAMOND_BOOTS, new ItemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2), new ItemEnchantment(Enchantment.PROTECTION_PROJECTILE, 5));

        // Weapon
        addChestItem(ChestItemCategory.WEAPON, true, 100, Material.DIAMOND_SWORD, new ItemEnchantment(Enchantment.DAMAGE_ALL, 1), new ItemEnchantment(Enchantment.FIRE_ASPECT, 2));
        addChestItem(ChestItemCategory.WEAPON, true, 100, Material.BOW, new ItemEnchantment(Enchantment.ARROW_DAMAGE, 5));
        addChestItem(ChestItemCategory.WEAPON, true, 100, Material.ARROW, 16);
        addChestItem(ChestItemCategory.WEAPON, true, 100, Material.ARROW, 64);
        // Potions
        addChestItem(ChestItemCategory.POTIONS, true, 100, Material.POTION, 16417, 1); // Regeneration II
        addChestItem(ChestItemCategory.POTIONS, true, 100, Material.POTION, 16418, 1); // Speed II
        addChestItem(ChestItemCategory.POTIONS, true, 100, Material.POTION, 16420, 1); // Poison I

        // Misc
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.SNOW_BALL, 64);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.EXP_BOTTLE, 64);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.ENDER_PEARL, 3);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.ENDER_PEARL, 5);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.FISHING_ROD, new ItemEnchantment(Enchantment.KNOCKBACK, 3)); // Knockback Fishing Rod
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.TNT, 10);
        addChestItem(ChestItemCategory.MISCELLANEOUS, true, 100, Material.FLINT_AND_STEEL);
        addChestItem(ChestItemCategory.TOOLS, true, 100, Material.DIAMOND_AXE, new ItemEnchantment(Enchantment.DIG_SPEED, 3));
        addChestItem(ChestItemCategory.TOOLS, true, 100, Material.DIAMOND_PICKAXE, new ItemEnchantment(Enchantment.DIG_SPEED, 3));
        addChestItem(ChestItemCategory.FOOD, true, 100, Material.GOLDEN_APPLE, 5);
        addChestItem(ChestItemCategory.FOOD, true, 100, Material.GOLDEN_APPLE, 8);
        addChestItem(ChestItemCategory.BLOCKS, true, 100, Material.LOG, 64);

    }
}
