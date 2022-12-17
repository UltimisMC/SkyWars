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
public class MagicianKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.POTION, 16398);

    public MagicianKit() {
        super("Magician", KitRarity.RARE);

        addBundle(GameType.NORMAL, new MagicianBundle());
        addBundle(GameType.INSANE, new MagicianBundle());
    }

    static class MagicianBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.MONSTER_EGG, 101));
            addItem(new KitItem(Material.LEATHER_HELMET));
            addItem(new KitItem(Material.POTION, 8206).displayName("Magician Potion").amount(2));
            addItem(new KitItem(Material.STICK).displayName("The Wand")
                    .itemEnchantment(Enchantment.DAMAGE_ALL, 4));
        }
    }
}
