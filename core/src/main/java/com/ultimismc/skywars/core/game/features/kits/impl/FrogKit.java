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
public class FrogKit extends Kit {

    private static final String FROG_HEAD_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzE3ODE4OWVmOGZhN2E1YjcyNGZiOTFkZjlhNDQ3ODRmZDg1NjQ4ZWQzZTNhY2Y2ZDBkZWQ3YjhjYWEzMGYwNyJ9fX0=";
    private final PurchasableDesign design = new PurchasableDesign(FROG_HEAD_TEXTURE);

    public FrogKit() {
        super("Frog", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new FrogBundle());
        addBundle(GameType.INSANE, new FrogBundle());
    }

    static class FrogBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(FROG_HEAD_TEXTURE).displayName("Frog's Hat"));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.LEATHER_BOOTS));
            addItem(new KitItem(Material.POTION, 8235).displayName("Frog's Potion"));
        }
    }
}
