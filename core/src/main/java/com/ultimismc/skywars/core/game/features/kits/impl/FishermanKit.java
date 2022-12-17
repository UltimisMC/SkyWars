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
public class FishermanKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.FISHING_ROD);

    public FishermanKit() {
        super("Fisherman", KitRarity.RARE);

        addBundle(GameType.NORMAL, new FishermanBundle());
        addBundle(GameType.INSANE, new FishermanBundle());
    }

    static class FishermanBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.FISHING_ROD)
                    .itemEnchantment(Enchantment.DURABILITY, 10)
                    .itemEnchantment(Enchantment.KNOCKBACK, 1)
                    .itemEnchantment(Enchantment.LUCK, 10)
                    .itemEnchantment(Enchantment.LURE, 7));
            addItem(new KitItem(Material.COOKED_FISH).amount(16));
        }
    }
}
