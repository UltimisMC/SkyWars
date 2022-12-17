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
public class SnowmanKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.SNOW_BALL);

    public SnowmanKit() {
        super("Snowman", KitRarity.RARE);

        addBundle(GameType.NORMAL, new SnowmanBundle());
        addBundle(GameType.INSANE, new SnowmanBundle());
    }

    static class SnowmanBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.SNOW_BALL).amount(16));
            addItem(new KitItem(Material.SNOW_BLOCK).amount(2));
            addItem(new KitItem(Material.IRON_SPADE));
            addItem(new KitItem(Material.PUMPKIN));
        }
    }
}
