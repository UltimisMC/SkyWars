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

    private final Perk perk;
    private final PerkProduct perkProduct;
    private UserAsset perkAsset;

    public PurchasedPerkProduct(Perk perk) {
        super(perk.getName(), 0);

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

        List<String> itemLore = productItemDesign.getLore();
        boolean activated = perkAsset.isActivated();
        itemLore.add("&eClick to toggle " + (activated ? "&cOFF" : "&aON"));
        return productItemDesign;
    }

    @Override
    public void executeAction(User user, ClickType clickType) {
        Player player = user.getPlayer();
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);

        boolean activated = perkAsset.isActivated();
        ShopMessageKeys toggleMessage = ShopMessageKeys.PERK_EQUIPPED_MESSAGE;
        if(activated) {
            toggleMessage = ShopMessageKeys.PERK_UN_EQUIPPED_MESSAGE;
        }
        perkAsset.toggleAsset();
        toggleMessage.sendMessage(player, new Replacement("name", perk.getName()));
    }
}
