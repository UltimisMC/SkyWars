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
public class RookieKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.LEATHER_HELMET);

    public RookieKit() {
        super("Rookie", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new NormalRookieBundle());
        addBundle(GameType.INSANE, new InsaneRookieBundle());
    }

    static class NormalRookieBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.LEATHER_HELMET);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.LEATHER_HELMET));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.LEATHER_BOOTS));
            addItem(new KitItem(Material.WOOD_SWORD));
            addItem(new KitItem(Material.GLASS).amount(16));
            addItem(new KitItem(Material.COOKED_BEEF));
        }
    }

    static class InsaneRookieBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.LEATHER_HELMET);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.LEATHER_HELMET));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.LEATHER_BOOTS));
            addItem(new KitItem(Material.WOOD_SWORD));
            addItem(new KitItem(Material.GLASS).amount(16));
            addItem(new KitItem(Material.COOKED_BEEF));
        }
    }
}
