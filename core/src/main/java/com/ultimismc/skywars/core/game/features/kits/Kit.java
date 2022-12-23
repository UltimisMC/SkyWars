package com.ultimismc.skywars.core.game.features.kits;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.inventory.ItemEnchantment;

import java.util.*;

/**
 * @author DirectPlan
 */
@Getter
public abstract class Kit implements Purchasable {

    private final Currency currency = Currency.COIN_CURRENCY;

    private final String category = "Kit";
    private final String name;
    private final KitRarity kitRarity;
    private final boolean soulWell;

    private final Map<GameType, KitBundle> gameBundles = new HashMap<>();

    public Kit(String name, KitRarity rarity, boolean soulWellKit) {
        this.name = name;
        this.kitRarity = rarity;
        this.soulWell = soulWellKit;
    }

    public Kit(String name, KitRarity rarity) {
        this(name, rarity, true);
    }

    @Override
    public int getPrice() {
        return kitRarity.getPrice();
    }

    @Override
    public PurchasableRarity getRarity() {
        if(kitRarity != null) {
            return kitRarity.getPurchasableRarity();
        }
        return null;
    }

    @Override
    public PurchasableDesign getDesign() {
        return new PurchasableDesign(Material.WOOD_PICKAXE);
    }

    public PurchasableDesign getKitDesign(GameType gameType) {
        KitBundle kitBundle = getBundle(gameType);
        PurchasableDesign design = kitBundle.getDesign();
        if(design == null) return getDesign(); // #getDesign is like a common design for kits of normal & insane.
        return design;
    }

    public List<String> getDisplayItems(GameType gameType) {
        List<String> displayItems = new ArrayList<>();
        List<KitItem> items = getItems(gameType);

        for(KitItem item : items) {
            int amount = item.getAmount();
            String displayName = item.getDisplayName();
            if(item.isDescriptionItem()) displayName = item.getDescription();
            displayItems.add(ChatColor.GRAY + displayName + (amount > 1 ? " x" + amount : ""));
            for(ItemEnchantment enchantment : item.getEnchantments()) {
                displayItems.add(ChatColor.GRAY + "   * " + enchantment.getDisplayName());
            }
        }
        return displayItems;
    }

    public List<KitItem> getItems(GameType gameType) {
        KitBundle kitBundle = getBundle(gameType);
        return kitBundle.getItems();
    }

    public void addBundle(GameType gameType, KitBundle kitBundle) {
        gameBundles.put(gameType, kitBundle);
    }

    public KitBundle getBundle(GameType gameType) {
        return gameBundles.get(gameType);
    }

    @Getter
    static class DummyKitBundle extends KitBundle {

        private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.IRON_SWORD));
            addItem(new KitItem(Material.STONE).amount(64));
            addItem(new KitItem(Material.EGG).amount(64));
            addItem(new KitItem(Material.DIAMOND_CHESTPLATE));
            addItem(new KitItem(Material.GOLD_BOOTS));
        }
    }

}
