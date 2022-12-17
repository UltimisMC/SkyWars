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
public class TrollKit extends Kit  {

    private final PurchasableDesign design = new PurchasableDesign(Material.WEB);

    public TrollKit() {
        super("Troll", KitRarity.LEGENDARY);

        addBundle(GameType.NORMAL, new TrollBundle());
        addBundle(GameType.INSANE, new TrollBundle());
    }

    static class TrollBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.WEB).amount(16));
            addItem(new KitItem(Material.FIREWORK).amount(5));
            addItem(new KitItem(Material.LEATHER_HELMET));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.LEATHER_BOOTS));
        }
    }
}
