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
public class HealerKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.CAKE);

    public HealerKit() {
        super("Healer", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new HealerBundle());
        addBundle(GameType.INSANE, new HealerBundle());
    }

    static class HealerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.POTION, 16389).amount(3));
            addItem(new KitItem(Material.POTION, 16385).amount(2));
        }
    }
}
