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
public class CactusKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.CACTUS);

    public CactusKit() {
        super("Cactus", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new NormalCactusBundle());
        addBundle(GameType.INSANE, new InsaneCactusBundle());
    }

    static class NormalCactusBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.CACTUS);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.CACTUS).amount(8));
            addItem(new KitItem(Material.SAND).amount(16));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE)
                    .itemEnchantment(Enchantment.THORNS, 5)
                    .itemEnchantment(Enchantment.DURABILITY, 5));
            addItem(new KitItem(Material.LEATHER_HELMET)
                    .itemEnchantment(Enchantment.THORNS, 5)
                    .itemEnchantment(Enchantment.DURABILITY, 5));
        }
    }

    static class InsaneCactusBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.CACTUS);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.CACTUS).amount(8));
            addItem(new KitItem(Material.SAND).amount(16));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE)
                    .itemEnchantment(Enchantment.THORNS, 5)
                    .itemEnchantment(Enchantment.DURABILITY, 5));
            addItem(new KitItem(Material.LEATHER_HELMET)
                    .itemEnchantment(Enchantment.THORNS, 5)
                    .itemEnchantment(Enchantment.DURABILITY, 5));
        }
    }
}
