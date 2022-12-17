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
public class BaseballPlayerKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_HELMET); // Chainmail helmet?

    public BaseballPlayerKit() {
        super("Baseball Player", KitRarity.RARE);

        addBundle(GameType.NORMAL, new NormalBaseballPlayerBundle());
        addBundle(GameType.INSANE, new InsaneBaseballPlayerBundle());
    }

    static class NormalBaseballPlayerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.IRON_HELMET);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.IRON_HELMET).
                    itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
            addItem(new KitItem(Material.WOOD_SWORD)
                    .itemEnchantment(Enchantment.KNOCKBACK, 1));
        }
    }

    static class InsaneBaseballPlayerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.IRON_HELMET);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.IRON_HELMET).
                    itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
            addItem(new KitItem(Material.WOOD_SWORD)
                    .itemEnchantment(Enchantment.KNOCKBACK, 1));
        }
    }
}
