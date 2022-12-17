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
public class EcologistKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_AXE);

    public EcologistKit() {
        super("Ecologist", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new NormalEcologistBundle());
        addBundle(GameType.INSANE, new InsaneEcologistBundle());
    }

    static class NormalEcologistBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.IRON_AXE);

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.IRON_AXE)
                    .itemEnchantment(Enchantment.DIG_SPEED, 2)
                    .itemEnchantment(Enchantment.DAMAGE_ALL, 1));
            addItem(new KitItem(Material.WOOD).amount(16));
        }
    }

    static class InsaneEcologistBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.DIAMOND_AXE);

        @Override
        public void buildGameItems() {

            addItem(new KitItem(Material.DIAMOND_AXE)
                    .itemEnchantment(Enchantment.DIG_SPEED, 2)
                    .itemEnchantment(Enchantment.DAMAGE_ALL, 1));
            addItem(new KitItem(Material.WOOD).amount(32));
        }
    }
}
