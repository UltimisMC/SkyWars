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

/**
 * @author DirectPlan
 */
@Getter
public class SalmonKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.COOKED_FISH);

    public SalmonKit() {
        super("Salmon", KitRarity.LEGENDARY);

        addBundle(GameType.NORMAL, new NormalSalmonBundle());
        addBundle(GameType.INSANE, new InsaneSalmonBundle());
    }

    static class NormalSalmonBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.LEATHER_BOOTS)
                    .itemEnchantment(Enchantment.DEPTH_STRIDER, 3)
                    .itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3));
            addItem(new KitItem(Material.LEATHER_HELMET)
                    .itemEnchantment(Enchantment.WATER_WORKER, 1)
                    .itemEnchantment(Enchantment.OXYGEN, 3));
            addItem(new KitItem(Material.WATER_BUCKET).amount(3));
            addItem(new KitItem(Material.IRON_BOOTS));
        }
    }

    static class InsaneSalmonBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.LEATHER_BOOTS)
                    .itemEnchantment(Enchantment.DEPTH_STRIDER, 3)
                    .itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3));
            addItem(new KitItem(Material.LEATHER_HELMET)
                    .itemEnchantment(Enchantment.WATER_WORKER, 1)
                    .itemEnchantment(Enchantment.OXYGEN, 3));
            addItem(new KitItem(Material.WATER_BUCKET).amount(3));
            addItem(new KitItem(Material.DIAMOND_CHESTPLATE));
        }
    }
}
