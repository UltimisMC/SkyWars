package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class PurchasedPerkProduct extends UserProduct {

    private final PurchasedPerksCategory perksCategory;
    private final Perk perk;
    private final PerkProduct perkProduct;
    private UserAsset perkAsset;

    public PurchasedPerkProduct(PurchasedPerksCategory perksCategory, Perk perk) {
        super(perk.getName(), 0);

        this.perksCategory = perksCategory;
        this.perk = perk;
        perkProduct = new PerkProduct(perk);
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        perkAsset = user.getAsset(perk);
        if(perkAsset == null) {
            return null;
        }
        ProductItemDesign productItemDesign = perkProduct.designProduct(user);

        boolean activated = perkAsset.isActivated();
        List<String> itemLore = productItemDesign.getLore();
        itemLore.add("&eClick to toggle " + (activated ? "&cOFF" : "&aON"));

        productItemDesign.setInvisibleEnchanted(activated);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user, ClickType clickType) {
        perksCategory.togglePerk(user, perkAsset);
    }

    @Override
    public boolean isRefreshInventoryEnabled() {
        return true;
    }
}
