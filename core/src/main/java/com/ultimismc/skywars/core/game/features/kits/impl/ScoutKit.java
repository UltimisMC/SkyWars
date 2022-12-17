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
public class ScoutKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.POTION, 2); // 16450?

    public ScoutKit() {
        super("Scout", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new ScoutBundle());
        addBundle(GameType.INSANE, new ScoutBundle());
    }

    static class ScoutBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.WOOD_SWORD));
            addItem(new KitItem(Material.POTION, 16450).amount(2));
        }
    }
}
