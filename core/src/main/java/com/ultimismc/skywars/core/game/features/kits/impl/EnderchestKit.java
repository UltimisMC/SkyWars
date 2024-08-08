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
public class EnderchestKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.ENDER_CHEST);

    public EnderchestKit() {
        super("Enderchest", KitRarity.RARE);

        addBundle(GameType.NORMAL, new EnderchestBundle());
        addBundle(GameType.INSANE, new EnderchestBundle());
    }

    static class EnderchestBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.GOLDEN_APPLE));
            // Add some code here for 4th chest
        }
    }
}
