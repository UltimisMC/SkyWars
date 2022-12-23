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
public class ArmorsmithKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.ANVIL);

    public ArmorsmithKit() {
        super("Armorsmith", KitRarity.COMMON);

        addBundle(GameType.NORMAL, new NormalArmorsmithBundle());
        addBundle(GameType.INSANE, new InsaneArmorsmithKitBundle());
    }

    @Override
    public boolean isDefault() {
        return true;
    }

    static class NormalArmorsmithBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.ANVIL);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.ANVIL));
            addItem(new KitItem(Material.ENCHANTED_BOOK).
                    itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                    .itemEnchantment(Enchantment.DAMAGE_ALL, 1));
            addItem(new KitItem(Material.EXP_BOTTLE).amount(64));
            addItem(new KitItem(Material.DIAMOND_HELMET));
        }
    }

    static class InsaneArmorsmithKitBundle extends KitBundle {

        @Getter private final PurchasableDesign design = new PurchasableDesign(Material.ANVIL);

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.ANVIL));
            addItem(new KitItem(Material.ENCHANTED_BOOK).
                    itemEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                    .itemEnchantment(Enchantment.DAMAGE_ALL, 1));
            addItem(new KitItem(Material.EXP_BOTTLE).amount(64));
            addItem(new KitItem(Material.DIAMOND_HELMET));
        }
    }
}
