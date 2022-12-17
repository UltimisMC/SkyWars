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
public class CannoneerKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.TNT);

    public CannoneerKit() {
        super("Cannoneer", KitRarity.LEGENDARY);

        addBundle(GameType.NORMAL, new NormalCannoneerBundle());
        addBundle(GameType.INSANE, new InsaneCannoneerBundle());
    }


    static class NormalCannoneerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.TNT).amount(16));
            addItem(new KitItem(Material.REDSTONE_BLOCK).amount(4));
            addItem(new KitItem(Material.IRON_BOOTS)
                    .itemEnchantment(Enchantment.PROTECTION_FALL, 3)
                    .itemEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3));
            addItem(new KitItem(Material.WATER_BUCKET));
            addItem(new KitItem(Material.STONE_PLATE).amount(4));
        }
    }

    static class InsaneCannoneerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.TNT).amount(16));
            addItem(new KitItem(Material.REDSTONE_BLOCK).amount(4));
            addItem(new KitItem(Material.DIAMOND_BOOTS)
                    .itemEnchantment(Enchantment.PROTECTION_FALL, 3)
                    .itemEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3));
            addItem(new KitItem(Material.WATER_BUCKET));
            addItem(new KitItem(Material.STONE_PLATE).amount(4));
        }
    }
}
