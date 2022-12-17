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
public class BatguyKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.LEATHER_HELMET);

    public BatguyKit() {
        super("Batguy", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new NormalBatguyBundle());
        addBundle(GameType.INSANE, new InsaneBatguyBundle());
    }

    @Override
    public boolean isDefault() {
        return true;
    }

    static class NormalBatguyBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.LEATHER_HELMET);

        @Override
        public void buildGameItems() {
            // Add color to leather armor
            addItem(new KitItem(Material.LEATHER_HELMET));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.LEATHER_BOOTS));
            addItem(new KitItem(Material.POTION).amount(2).displayName("&aBatguy's Potion"));
            addItem(new KitItem(Material.EGG).amount(5).displayName("Bat Egg"));
        }
    }

    static class InsaneBatguyBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.LEATHER_HELMET);

        @Override
        public void buildGameItems() {
            // Add color to leather armor
            addItem(new KitItem(Material.LEATHER_HELMET));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.LEATHER_BOOTS));
            addItem(new KitItem(Material.POTION).amount(2).displayName("&aBatguy's Potion"));
            addItem(new KitItem(Material.EGG).amount(5).displayName("Bat Egg"));

        }
    }
}
