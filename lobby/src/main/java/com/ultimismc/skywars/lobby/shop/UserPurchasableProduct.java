package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public abstract class UserPurchasableProduct extends UserConfirmableProduct {

    private Purchasable purchasable;

    protected final Currency currency;
    private final int cost;
    private final GameType gameType;

    public UserPurchasableProduct(String name, int itemSlot, int cost, Currency currency, GameType gameType) {
        super(name, itemSlot);
        this.cost = cost;
        this.currency = currency;
        this.gameType = gameType;
    }

    public UserPurchasableProduct(String name, int itemSlot, int cost, Currency currency) {
        this(name, itemSlot, cost, currency, null);
    }

    public UserPurchasableProduct(String name, int inventoryRows, int itemSlot, int cost, Currency currency, GameType gameType) {
        super(name, inventoryRows, itemSlot);
        this.cost = cost;
        this.currency = currency;
        this.gameType = gameType;
    }

    public UserPurchasableProduct(String name, int inventoryRows, int itemSlot, int cost, Currency currency) {
        this(name, inventoryRows, itemSlot, cost, currency, null);
    }

    public UserPurchasableProduct(int itemSlot, Purchasable purchasable, GameType gameType) {
        this(purchasable.getName(), itemSlot, purchasable.getPrice(), purchasable.getCurrency(), gameType);
        this.purchasable = purchasable;
    }

    public UserPurchasableProduct(int itemSlot, Purchasable purchasable) {
        this(itemSlot, purchasable, null);
    }

    public UserPurchasableProduct(int itemSlot, int inventoryRows, Purchasable purchasable, GameType gameType) {
        this(purchasable.getNameWithCategory(), inventoryRows, itemSlot, purchasable.getPrice(), purchasable.getCurrency(), gameType);
        this.purchasable = purchasable;
    }

    public UserPurchasableProduct(int itemSlot, int inventoryRows, Purchasable purchasable) {
        this(itemSlot, inventoryRows, purchasable, null);
    }

    public abstract ProductItemDesign designPurchasableProduct(User user);

    public abstract void executePurchasableProduct(User user, GameType gameType, ClickType clickType);

    @Override
    public ProductItemDesign designProduct(User user) {
        if(purchasable != null && (ignoreDefaults() && purchasable.isDefault())) return null;

        ProductItemDesign shopProductItemDesign = designPurchasableProduct(user);
        if(shopProductItemDesign == null) return null;

        String displayName = shopProductItemDesign.getDisplayName();
        if(displayName == null) {
            displayName = getName();
        }
        boolean canAfford = currency.canAfford(user, cost);
        boolean hasPurchased = (purchasable != null && user.hasPurchased(purchasable, gameType));

        Material material = shopProductItemDesign.getMaterial();
        short durability = shopProductItemDesign.getDurability();
        ChatColor color = shopProductItemDesign.getColor();
        List<String> lore = new ArrayList<>(shopProductItemDesign.getLore());
        String productTexture = shopProductItemDesign.getSkullTexture();
        String purchaseStatus = shopProductItemDesign.getPurchaseStatus();
        if(color == null) {
            color = ChatColor.GREEN;
            if(!hasPurchased) {
                color = (canAfford ? ChatColor.GREEN : ChatColor.RED);
            }
        }
        displayName = color + displayName;

        if(!hasPurchased) {
            lore.add("&7Cost: " + getDisplayCost());
            if(purchasable != null && purchasable.isSoulWell()) {
                lore.add("&bAlso found in the Soul Well!");
            }
            lore.add(" ");
            String status = ShopMessageKeys.SHOP_ITEM_STATUS_CLICK_TO_PURCHASE.getStringValue();
            if(purchaseStatus != null) {
                status = purchaseStatus;
            }
            if(!canAfford) {
                status = ShopMessageKeys.SHOP_ITEM_STATUS_INSUFFICIENT_FUNDS.getStringValue();
            }
            lore.add(status);
        }else {
            lore.add("&aUNLOCKED");
            String purchasedStatus = shopProductItemDesign.getPurchasedStatus();
            if(purchasedStatus != null) {
                lore.add(" ");
                lore.add(purchasedStatus);
            }
        }
        ProductItemDesign productItemDesign = new ProductItemDesign(material, durability, color, lore, hasPurchased || canAfford);
        productItemDesign.setIgnoreConfirmation(hasPurchased);
        productItemDesign.setDisplayName(displayName);
        productItemDesign.setSkullTexture(productTexture);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user, ClickType clickType) {
        Player player = user.getPlayer();

        if(purchasable != null && user.hasPurchased(purchasable, gameType)) {
            executePurchasableProduct(user, gameType, clickType);
            return;
        }
        currency.decreaseCurrency(user, cost);
        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
        if(purchasable != null) { // Purchasable exists but not purchased
            player.closeInventory();
            String displayPrice = getDisplayCost();

            user.purchaseAsset(purchasable, gameType);
            ShopMessageKeys.SHOP_ITEM_PURCHASED_MESSAGE.sendMessage(player, new Replacement("name", purchasable.getNameWithCategory()),
                    new Replacement("price", displayPrice));
            return;
        }
        executePurchasableProduct(user, gameType, clickType);
    }

    protected String getDisplayCost() {
        return currency.getDisplayAmount(cost);
    }

    public boolean ignoreDefaults() {
        return true;
    }
}
