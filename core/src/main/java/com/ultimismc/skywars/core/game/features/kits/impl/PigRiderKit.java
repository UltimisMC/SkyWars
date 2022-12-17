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
public class PigRiderKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.CARROT_STICK);

    public PigRiderKit() {
        super("Pig Rider", KitRarity.RARE);

        addBundle(GameType.NORMAL, new PigRiderBundle());
        addBundle(GameType.INSANE, new PigRiderBundle());
    }

    static class PigRiderBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.SADDLE));
            addItem(new KitItem(Material.MONSTER_EGG, 90));
            addItem(new KitItem(Material.GOLD_HELMET)
                    .itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4));
            addItem(new KitItem(Material.CARROT_STICK));
        }
    }
}
