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
public class DefaultKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_PICKAXE);

    public DefaultKit() {
        super("Default", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new NormalKitBundle());
        addBundle(GameType.INSANE, new InsaneKitBundle());
    }

    @Override
    public boolean isDefault() {
        return true;
    }

    static class NormalKitBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.WOOD_PICKAXE);

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.WOOD_PICKAXE));
            addItem(new KitItem(Material.WOOD_AXE));
            addItem(new KitItem(Material.WOOD_HOE));
        }
    }

    static class InsaneKitBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.DIAMOND_PICKAXE);

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.DIAMOND_PICKAXE));
            addItem(new KitItem(Material.DIAMOND_AXE));
            addItem(new KitItem(Material.DIAMOND_HOE));
        }
    }
}
