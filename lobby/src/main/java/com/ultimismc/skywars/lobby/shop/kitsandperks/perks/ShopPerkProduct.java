package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserAsset;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class ShopPerkProduct extends PerkProduct {

    public ShopPerkProduct(Perk perk) {
        super(perk);
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        UserAsset perkAsset = user.getAsset(perk);
        if(perkAsset != null) return null;

        ProductItemDesign productItemDesign = super.designProduct(user);

        List<String> lore = new ArrayList<>();
        lore.add(" ");
        boolean canAfford = user.canAfford(perk);
        String status = "&eClick to purchase!";
        if(!canAfford) {
            status = ShopMessageKeys.SHOP_ITEM_STATUS_INSUFFICIENT_FUNDS.getStringValue();
        }
        lore.add(status);
        productItemDesign.getLore().addAll(lore);
        boolean canAffordPerk = user.canAfford(perk);
        productItemDesign.setCanAfford(canAffordPerk);

        return productItemDesign;
    }

    @Override
    public void executeAction(User user) {
        Player player = user.getPlayer();
        user.purchaseAsset(perk);

        int price = perk.getPrice();
        Currency currency = perk.getCurrency();
        currency.decreaseCurrency(user, price);

        ShopMessageKeys.SHOP_ITEM_PURCHASED_MESSAGE.sendMessage(player, new Replacement("name", perk.getName()),
                new Replacement("price", price));
    }
}
