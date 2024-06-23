package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class OwnedPerkProduct extends UserProduct {

    private final OwnedPerksCategory ownedPerksCategory;
    private final Perk perk;
    private final GameType gameType;
    private UserAsset perkAsset;

    public OwnedPerkProduct(OwnedPerksCategory ownedPerksCategory, Perk perk, GameType gameType) {
        super(perk.getName(), 0);

        this.ownedPerksCategory = ownedPerksCategory;
        this.perk = perk;
        this.gameType = gameType;
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        ProductItemDesign productItemDesign = PerkProductDesigner.designProduct(perk, user);

        boolean activated = perkAsset.isActivated();
        List<String> itemLore = productItemDesign.getLore();
        itemLore.add("&eClick to toggle " + (activated ? "&cOFF" : "&aON"));

        productItemDesign.setInvisibleEnchanted(activated);
        return productItemDesign;
    }

    @Override
    public boolean isProductVisible(User user) {
        perkAsset = user.getAsset(perk, gameType);

        return perkAsset != null;
    }

    @Override
    public void executeAction(User user, ClickType clickType) {
        ownedPerksCategory.togglePerk(user, perkAsset);
    }

    @Override
    public boolean isRefreshInventoryEnabled() {
        return true;
    }
}
