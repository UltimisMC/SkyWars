package com.ultimismc.skywars.core.game.features.kits;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.inventory.ItemEnchantment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractKit implements Kit {

    private final Currency currency = Currency.COIN_CURRENCY;

    private final String category = "Kit";
    private final String name;
    private final KitRarity kitRarity;
    private final boolean soulWell;

    private final List<KitItem> items = new ArrayList<>();

    public AbstractKit(String name, KitRarity rarity, boolean soulWellKit) {
        this.name = name;
        this.kitRarity = rarity;
        this.soulWell = soulWellKit;
    }

    public AbstractKit(String name, KitRarity rarity) {
        this(name, rarity, true);
    }

    @Override
    public int getPrice() {
        return kitRarity.getPrice();
    }

    public void addKitItem(KitItem item) {
        items.add(item);
    }

    @Override
    public PurchasableRarity getRarity() {
        if(kitRarity != null) {
            return kitRarity.getPurchasableRarity();
        }
        return null;
    }

    @Override
    public List<String> getDisplayItems() {
        List<String> displayItems = new ArrayList<>();
        for(KitItem item : items) {
            displayItems.add(ChatColor.GRAY + item.getDisplayName());
            for(ItemEnchantment enchantment : item.getEnchantments()) {
                displayItems.add(ChatColor.GRAY + "   * " + enchantment.getDisplayName());
            }
        }
        return displayItems;
    }
}
