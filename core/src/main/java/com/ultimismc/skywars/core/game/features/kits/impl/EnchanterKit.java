package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.game.features.kits.KitBundle;
import com.ultimismc.skywars.core.game.features.kits.KitItem;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class EnchanterKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.ENCHANTMENT_TABLE);

    public EnchanterKit() {
        super("Enchanter", KitRarity.RARE);

        addBundle(GameType.NORMAL, new NormalEnchanterBundle());
        addBundle(GameType.INSANE, new InsaneEnchanterBundle());
    }

    static class NormalEnchanterBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.ENCHANTMENT_TABLE);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.ENCHANTMENT_TABLE));
            addItem(new KitItem(Material.EXP_BOTTLE).amount(64));
            addItem(new KitItem(Material.BOOK).amount(8));
        }
    }

    static class InsaneEnchanterBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.ENCHANTMENT_TABLE);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.ENCHANTMENT_TABLE));
            addItem(new KitItem(Material.EXP_BOTTLE).amount(64));
            addItem(new KitItem(Material.BOOK).amount(8));
        }
    }
}
