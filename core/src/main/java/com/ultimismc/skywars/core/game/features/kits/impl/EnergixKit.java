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
public class EnergixKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.BREWING_STAND_ITEM);

    public EnergixKit() {
        super("Energix", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new EnergixBundle());
        addBundle(GameType.INSANE, new EnergixBundle());
    }

    static class EnergixBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.BREWING_STAND_ITEM);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.POTION, 8201));
        }
    }
}
