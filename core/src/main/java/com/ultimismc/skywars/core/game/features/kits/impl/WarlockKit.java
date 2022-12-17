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
public class WarlockKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.BREWING_STAND_ITEM);

    public WarlockKit() {
        super("Warlock", KitRarity.RARE);

        addBundle(GameType.NORMAL, new WarlockBundle());
        addBundle(GameType.INSANE, new WarlockBundle());
    }

    static class WarlockBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.POTION, 16396).amount(2));
            addItem(new KitItem(Material.POTION, 16392).amount(2));
            addItem(new KitItem(Material.POTION, 16388));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE)
                    .itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2));
        }
    }
}
