package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
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

    public UserPurchasableProduct(String name, int itemSlot, int cost, Currency currency) {
        super(name, itemSlot);
        this.cost = cost;
        this.currency = currency;
    }

    public UserPurchasableProduct(String name, int inventoryRows, int itemSlot, int cost, Currency currency) {
        super(name, inventoryRows, itemSlot);
        this.cost = cost;
        this.currency = currency;
    }

    public UserPurchasableProduct(int itemSlot, Purchasable purchasable) {
        this(purchasable.getNameWithCategory(), itemSlot, purchasable.getPrice(), purchasable.getCurrency());
        this.purchasable = purchasable;
    }

    public UserPurchasableProduct(int itemSlot, int inventoryRows, Purchasable purchasable) {
        this(purchasable.getNameWithCategory(), inventoryRows, itemSlot, purchasable.getPrice(), purchasable.getCurrency());
        this.purchasable = purchasable;
    }

    public abstract ProductItemDesign designPurchasableProduct(User user);

    public abstract void executePurchasableProduct(User user);

    @Override
    public ProductItemDesign designProduct(User user) {

        ProductItemDesign shopProductItemDesign = designPurchasableProduct(user);
        if(shopProductItemDesign == null) return null;

        String displayName = shopProductItemDesign.getDisplayName();
        if(displayName == null) {
            displayName = getName();
        }
        boolean canAfford = currency.canAfford(user, cost);
        boolean hasPurchased = (purchasable != null && user.hasPurchased(purchasable));

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
            lore.add("&7Cost: " + currency.getDisplayAmount(cost));
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
    public void executeAction(User user) {
        Player player = user.getPlayer();

        if(purchasable != null && user.hasPurchased(purchasable)) {
            executePurchasableProduct(user);
            return;
        }
        currency.decreaseCurrency(user, cost);
        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
        if(purchasable != null) { // Purchasable exists but not purchased
            player.closeInventory();
            String displayPrice = currency.getDisplayAmount(cost);

            user.purchaseAsset(purchasable);
            ShopMessageKeys.SHOP_ITEM_PURCHASED_MESSAGE.sendMessage(player, new Replacement("name", purchasable.getNameWithCategory()),
                    new Replacement("price", displayPrice));
            return;
        }
        executePurchasableProduct(user);
    }
}
