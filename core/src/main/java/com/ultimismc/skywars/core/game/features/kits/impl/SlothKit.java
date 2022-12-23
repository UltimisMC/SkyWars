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
public class SlothKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.POTION, 9);

    public SlothKit() {
        super("Sloth", KitRarity.RARE);

        addBundle(GameType.NORMAL, new SlothBundle());
        addBundle(GameType.INSANE, new SlothBundle());
    }

    static class SlothBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.POTION, 9).displayName("Sloth Potion").amount(3));
            addItem(new KitItem(Material.LEATHER_HELMET));
            addItem(new KitItem(Material.LEATHER_CHESTPLATE));
            addItem(new KitItem(Material.LEATHER_LEGGINGS));
            addItem(new KitItem(Material.LEATHER_BOOTS));
            addItem(new KitItem(Material.WOOD, 1).amount(4)); // Jungle Wood btw check
            addItem(new KitItem(Material.AIR).description("Has permanent slowness II"));
        }
    }
}
