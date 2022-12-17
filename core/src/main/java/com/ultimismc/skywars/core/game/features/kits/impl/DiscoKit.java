package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.game.features.kits.KitBundle;
import com.ultimismc.skywars.core.game.features.kits.KitItem;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@Getter
public class DiscoKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.JUKEBOX);

    public DiscoKit() {
        super("Disco", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new NormalDiscoBundle());
        addBundle(GameType.INSANE, new InsaneDiscoBundle());
    }

    static class NormalDiscoBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.JUKEBOX);

        @Override
        public void buildGameItems() {
            // Leather color
            addItem(new KitItem(Material.GOLD_HELMET)
                    .itemEnchantment(Enchantment.PROTECTION_PROJECTILE, 2));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE)
                    .itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.LEATHER_BOOTS)
                    .itemEnchantment(Enchantment.PROTECTION_FALL, 2));
            addItem(new KitItem(Material.JUKEBOX));
            addItem(new KitItem(Material.NOTE_BLOCK, 64));

            int randomRecordNum = PluginUtility.getRandomInteger(3, 12);
            Material randomRecord = Material.valueOf("RECORD_" + randomRecordNum);
            addItem(new KitItem(randomRecord).displayName("&aRandom Music Disc"));
        }
    }

    static class InsaneDiscoBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.JUKEBOX);

        @Override
        public void buildGameItems() {
            // Leather color
            addItem(new KitItem(Material.GOLD_HELMET)
                    .itemEnchantment(Enchantment.PROTECTION_PROJECTILE, 2));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE)
                    .itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.LEATHER_BOOTS)
                    .itemEnchantment(Enchantment.PROTECTION_FALL, 2));
            addItem(new KitItem(Material.JUKEBOX));
            addItem(new KitItem(Material.NOTE_BLOCK, 64));

            int randomRecordNum = PluginUtility.getRandomInteger(3, 12);
            Material randomRecord = Material.valueOf("RECORD_" + randomRecordNum);
            addItem(new KitItem(randomRecord).displayName("&aRandom Music Disc"));

        }
    }
}
