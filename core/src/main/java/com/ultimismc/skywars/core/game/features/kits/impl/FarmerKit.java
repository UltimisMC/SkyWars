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
public class FarmerKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.DIAMOND_LEGGINGS);

    public FarmerKit() {
        super("Farmer", KitRarity.RARE);

        addBundle(GameType.NORMAL, new NormalFarmerBundle());
        addBundle(GameType.INSANE, new InsaneFarmerBundle());
    }

    static class NormalFarmerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.IRON_LEGGINGS);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.IRON_LEGGINGS)
                    .itemEnchantment(Enchantment.PROTECTION_PROJECTILE, 3));
            addItem(new KitItem(Material.EGG).amount(32));
            addItem(new KitItem(Material.GOLDEN_APPLE));
        }
    }

    static class InsaneFarmerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.DIAMOND_LEGGINGS);

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.DIAMOND_LEGGINGS)
                    .itemEnchantment(Enchantment.PROTECTION_PROJECTILE, 3));
            addItem(new KitItem(Material.EGG).amount(32));
            addItem(new KitItem(Material.GOLDEN_APPLE));
        }
    }
}
