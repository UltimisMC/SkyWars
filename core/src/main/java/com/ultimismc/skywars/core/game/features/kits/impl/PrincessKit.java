package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.game.features.kits.KitBundle;
import com.ultimismc.skywars.core.game.features.kits.KitItem;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * @author DirectPlan
 */
@Getter
public class PrincessKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.RED_ROSE);

    public PrincessKit() {
        super("Princess", KitRarity.RARE);

        addBundle(GameType.NORMAL, new PrincessBundle());
        addBundle(GameType.INSANE, new PrincessBundle());
    }
    static class PrincessBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.GOLD_HELMET)
                    .itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
            addItem(new KitItem(Material.BOW)
                    .itemEnchantment(Enchantment.ARROW_FIRE, 1));
            addItem(new KitItem(Material.ARROW));
        }
    }
}
