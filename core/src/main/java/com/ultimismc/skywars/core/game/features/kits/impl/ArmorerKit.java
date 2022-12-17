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
public class ArmorerKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.DIAMOND_CHESTPLATE);

    public ArmorerKit() {
        super("Armorer", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new NormalArmorerBundle());
        addBundle(GameType.INSANE, new InsaneArmorerBundle());
    }

    @Override
    public boolean isDefault() {
        return true;
    }

    static class NormalArmorerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.GOLD_CHESTPLATE);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.GOLD_CHESTPLATE));
            addItem(new KitItem(Material.GOLD_LEGGINGS));

        }
    }

    static class InsaneArmorerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.DIAMOND_CHESTPLATE);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.DIAMOND_CHESTPLATE));
            addItem(new KitItem(Material.DIAMOND_LEGGINGS));

        }
    }
}
