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
public class SlimeKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.SLIME_BLOCK);

    public SlimeKit() {
        super("Slime", KitRarity.LEGENDARY);

        addBundle(GameType.NORMAL, new NormalSlimeBundle());
        addBundle(GameType.INSANE, new InsaneSlimeBundle());
    }

    static class NormalSlimeBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.IRON_BOOTS)
                    .itemEnchantment(Enchantment.PROTECTION_FALL, 4));
            addItem(new KitItem(Material.SLIME_BLOCK).amount(16));
        }
    }

    static class InsaneSlimeBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.DIAMOND_BOOTS)
                    .itemEnchantment(Enchantment.PROTECTION_FALL, 4));
            addItem(new KitItem(Material.SLIME_BLOCK).amount(16));
        }
    }
}
