package com.ultimismc.skywars.lobby.shop.kitsandperks.kits;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.game.features.kits.KitItem;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import com.ultimismc.skywars.core.user.UserStatistics;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserConfirmableProduct;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.StringUtil;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.inventory.ItemEnchantment;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class KitProduct extends UserConfirmableProduct {

    private final Kit kit;

    public KitProduct(Kit kit) {
        super(kit.getName(), 0);
        this.kit = kit;
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        List<String> lore = new ArrayList<>();

        KitRarity rarity = kit.getRarity();
        List<KitItem> kitItems = kit.getItems();
        for(KitItem kitItem : kitItems) {

            lore.add(ChatColor.GRAY + kitItem.getDisplayName());
            for(ItemEnchantment enchantment : kitItem.getEnchantments()) {
                lore.add("   * " + enchantment.getDisplayName());
            }
        }
        lore.add(" ");
        lore.add("&7Rarity: " + rarity.getDisplayName());
        lore.add("&7Cost: " + kit.getDisplayPrice());
        if(kit.isSoulWell()) {
            lore.add("&bAlso found in the Soul Well!");
        }
        lore.add(" ");

        // TODO: Could be improved by declaring kitAsset beside kit field?
        UserAsset kitAsset = user.getAsset(kit);
        String status = "&eClick here to preview!";
        boolean canAfford = user.canAfford(kit);
        if(kitAsset == null) {
            status = ShopMessageKeys.SHOP_ITEM_STATUS_INSUFFICIENT_FUNDS.getStringValue();
            if(canAfford) {
                status = ShopMessageKeys.SHOP_ITEM_STATUS_CLICK_TO_PURCHASE.getStringValue();
            }
        }
        lore.add(status);

        return new ProductItemDesign(kit.getDisplayMaterial(), lore, canAfford);
    }

    @Override
    public void executeAction(User user) {
        Player player = user.getPlayer();

        UserAsset userAsset = user.getAsset(kit);
        if(userAsset == null) {
            user.purchaseAsset(kit);

            int price = kit.getPrice();
            Currency currency = kit.getCurrency();

            currency.decreaseCurrency(user, price);

            ShopMessageKeys.SHOP_ITEM_PURCHASED_MESSAGE.sendMessage(player, new Replacement("name", kit.getName()),
                    new Replacement("price", kit.getDisplayPrice()));
            return;
        }

        user.sendMessage("&aPreviewing &e" + kit.getName() + " &akit...");
    }
}
