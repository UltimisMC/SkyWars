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
public class PharaohKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.GOLD_HELMET);

    public PharaohKit() {
        super("Pharaoh", KitRarity.RARE);

        addBundle(GameType.NORMAL, new NormalPharaohBundle());
        addBundle(GameType.INSANE, new InsanePharaohBundle());
    }

    static class NormalPharaohBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.GOLD_HELMET);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.GOLD_HELMET));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.BEACON));
            addItem(new KitItem(Material.EMERALD_BLOCK).amount(42));
        }
    }

    static class InsanePharaohBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.IRON_HELMET);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.IRON_HELMET));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.BEACON));
            addItem(new KitItem(Material.EMERALD_BLOCK).amount(42));
        }
    }
}
