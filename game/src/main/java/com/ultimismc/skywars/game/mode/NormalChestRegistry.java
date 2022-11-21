package com.ultimismc.skywars.game.mode;

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
        addChestItem(false, 80, Material.IRON_HELMET);
        addChestItem(false, 60, Material.IRON_CHESTPLATE);
        addChestItem(false, 70, Material.IRON_LEGGINGS);
        addChestItem(false, 80, Material.IRON_BOOTS);

        addChestItem(false, 60, Material.GOLD_CHESTPLATE);
        addChestItem(false, 70, Material.GOLD_LEGGINGS);
        addChestItem(false, 50, Material.CHAINMAIL_LEGGINGS);

        addChestItem(false, 90, Material.LEATHER_HELMET);
        addChestItem(false, 70, Material.LEATHER_CHESTPLATE);
        addChestItem(false, 80, Material.LEATHER_LEGGINGS);
        addChestItem(false, 90, Material.LEATHER_BOOTS);

        addChestItem(false, 80, Material.STONE_PICKAXE);
        addChestItem(false, 60, Material.STONE_AXE);
        addChestItem(false, 90, Material.STONE_SWORD);
        addChestItem(false, 90, Material.STONE_SWORD, new ItemEnchantment(Enchantment.DAMAGE_ALL, 1));
        addChestItem(false, 70, Material.IRON_SWORD);
        addChestItem(false, 80, Material.IRON_PICKAXE);
        addChestItem(false, 80, Material.IRON_AXE);
        addChestItem(false, 40, Material.BOW);
        addChestItem(false, 40, Material.ARROW, 10);


        addChestItem(false, 60, Material.FISHING_ROD);
        addChestItem(false, 60, Material.WATER_BUCKET);
        addChestItem(false, 40, Material.LAVA_BUCKET);
        addChestItem(false, 40, Material.SNOW_BALL, 8);
        addChestItem(false, 60, Material.SNOW_BALL, 12);
        addChestItem(false, 20, Material.SNOW_BALL, 16);
        addChestItem(false, 60, Material.EGG, 8);
        addChestItem(false, 40, Material.EGG, 12);
        addChestItem(false, 20, Material.EGG, 16);
        addChestItem(false, 60, Material.COOKED_BEEF, 8);
        addChestItem(false, 40, Material.COOKED_BEEF, 12);
        addChestItem(false, 20, Material.COOKED_BEEF, 16);
        addChestItem(false, 60, Material.EXP_BOTTLE, 8);
        addChestItem(false, 40, Material.EXP_BOTTLE, 12);
        addChestItem(false, 20, Material.EXP_BOTTLE, 16);
        addChestItem(false, 50, Material.GOLDEN_APPLE, 1);
        addChestItem(false, 40, Material.GOLDEN_APPLE, 2);
        addChestItem(false, 90, Material.STONE, 16);
        addChestItem(false, 70, Material.STONE, 24);
        addChestItem(false, 50, Material.STONE, 32);
        addChestItem(false, 30, Material.STONE, 48);
        addChestItem(false, 90, Material.WOOD, 16);
        addChestItem(false, 70, Material.WOOD, 24);
        addChestItem(false, 50, Material.WOOD, 32);

        // Middle Items

        addChestItem(true, 100, Material.DIAMOND_HELMET, new ItemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
        addChestItem(true, 100, Material.IRON_CHESTPLATE, new ItemEnchantment(Enchantment.PROTECTION_PROJECTILE, 3));
        addChestItem(true, 100, Material.IRON_LEGGINGS, new ItemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4));
        addChestItem(true, 100, Material.DIAMOND_BOOTS, new ItemEnchantment(Enchantment.PROTECTION_FIRE, 5));

        addChestItem(true, 100, Material.IRON_SWORD, new ItemEnchantment(Enchantment.FIRE_ASPECT, 1));
        addChestItem(true, 100, Material.BOW, new ItemEnchantment(Enchantment.ARROW_DAMAGE, 1));

        addChestItem(true, 100, Material.ENDER_PORTAL);
        addChestItem(true, 100, Material.TNT, 10);
        addChestItem(true, 100, Material.DIAMOND_AXE, new ItemEnchantment(Enchantment.DIG_SPEED, 1));
        addChestItem(true, 100, Material.DIAMOND_PICKAXE, new ItemEnchantment(Enchantment.DIG_SPEED, 1));
        addChestItem(true, 100, Material.SNOW_BALL, 64);
        addChestItem(true, 100, Material.LAVA_BUCKET);
        addChestItem(true, 100, Material.EXP_BOTTLE, 64);
        addChestItem(true, 100, Material.FISHING_ROD, new ItemEnchantment(Enchantment.KNOCKBACK, 2));
        addChestItem(true, 100, Material.GOLDEN_APPLE);
        addChestItem(true, 100, Material.LOG, 64);


    }
}
