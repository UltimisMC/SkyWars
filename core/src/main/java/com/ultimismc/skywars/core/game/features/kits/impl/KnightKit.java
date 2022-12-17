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
public class KnightKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_SWORD);

    public KnightKit() {
        super("Knight", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new NormalKnightBundle());
        addBundle(GameType.INSANE, new InsaneKnightBundle());
    }

    static class NormalKnightBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.GOLD_SWORD);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.GOLD_SWORD)
                    .itemEnchantment(Enchantment.DAMAGE_ALL, 2)
                    .itemEnchantment(Enchantment.DURABILITY, 5));
            addItem(new KitItem(Material.GOLD_HELMET)
                    .itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        }
    }

    static class InsaneKnightBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.IRON_SWORD);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.IRON_SWORD)
                    .itemEnchantment(Enchantment.DAMAGE_ALL, 2)
                    .itemEnchantment(Enchantment.DURABILITY, 5));
            addItem(new KitItem(Material.IRON_HELMET)
                    .itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        }
    }
}
