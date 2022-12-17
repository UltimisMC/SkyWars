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
public class GrenadeKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.MONSTER_EGG, 50);

    public GrenadeKit() {
        super("Grenade", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new GrenadeBundle());
        addBundle(GameType.INSANE, new GrenadeBundle());
    }

    static class GrenadeBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.MONSTER_EGG, 50).displayName("Charged Creeper Egg"));
        }
    }
}
