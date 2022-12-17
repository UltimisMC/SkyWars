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
public class SpeleologistKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.DIAMOND_PICKAXE);

    public SpeleologistKit() {
        super("Speleologist", KitRarity.RARE);

        addBundle(GameType.NORMAL, new NormalSpeleologistBundle());
        addBundle(GameType.INSANE, new InsaneSpeleologistBundle());
    }

    static class NormalSpeleologistBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.IRON_PICKAXE);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.IRON_PICKAXE)
                    .itemEnchantment(Enchantment.DIG_SPEED, 3)
                    .itemEnchantment(Enchantment.DAMAGE_ALL, 1)
                    .itemEnchantment(Enchantment.DURABILITY, 3));
            addItem(new KitItem(Material.STONE).amount(16));
        }
    }

    static class InsaneSpeleologistBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.DIAMOND_PICKAXE);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.DIAMOND_PICKAXE)
                    .itemEnchantment(Enchantment.DIG_SPEED, 3)
                    .itemEnchantment(Enchantment.DAMAGE_ALL, 1)
                    .itemEnchantment(Enchantment.DURABILITY, 3));
            addItem(new KitItem(Material.STONE).amount(32));
        }
    }
}
