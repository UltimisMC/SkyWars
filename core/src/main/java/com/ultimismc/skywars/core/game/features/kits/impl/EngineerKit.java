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
public class EngineerKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.REDSTONE);

    public EngineerKit() {
        super("Engineer", KitRarity.RARE);

        addBundle(GameType.NORMAL, new EngineerBundle());
        addBundle(GameType.INSANE, new EngineerBundle());
    }

    static class EngineerBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.TRIPWIRE_HOOK).amount(2));
            addItem(new KitItem(Material.WEB).amount(8));
            addItem(new KitItem(Material.PISTON_BASE).amount(4));
            addItem(new KitItem(Material.SLIME_BALL).amount(4));
            addItem(new KitItem(Material.REDSTONE).amount(16));
            addItem(new KitItem(Material.LEVER));
            addItem(new KitItem(Material.DISPENSER).amount(2));
        }
    }
}
