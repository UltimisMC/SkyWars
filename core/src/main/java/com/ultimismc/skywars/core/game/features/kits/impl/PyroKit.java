package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.game.features.kits.KitBundle;
import com.ultimismc.skywars.core.game.features.kits.KitItem;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public class PyroKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.FLINT_AND_STEEL);

    public PyroKit() {
        super("Pyro", KitRarity.LEGENDARY);

        addBundle(GameType.NORMAL, new NormalPyroBundle());
        addBundle(GameType.INSANE, new InsanePyroBundle());
    }

    static class NormalPyroBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.FLINT_AND_STEEL));
            addItem(new KitItem(Material.LAVA_BUCKET).amount(2));
            addItem(new KitItem(Material.CHAINMAIL_CHESTPLATE));
            addItem(new KitItem(Material.CHAINMAIL_BOOTS));
            addItem(new KitItem(Material.POTION, 16451)); // Should be infinite
        }
    }

    static class InsanePyroBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.FLINT_AND_STEEL));
            addItem(new KitItem(Material.LAVA_BUCKET).amount(2));
            addItem(new KitItem(Material.IRON_CHESTPLATE));
            addItem(new KitItem(Material.IRON_BOOTS));
            addItem(new KitItem(Material.POTION, 16451)); // Should be infinite
        }
    }
}
