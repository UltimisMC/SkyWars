package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class PurchasedPerksCategory extends UserProductCategory {

    private final static int MAXIMUM_PERKS_SELECTION = 6;

    public PurchasedPerksCategory(int itemSlot) {
        super("Purchased Perks", itemSlot, true);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        return new ProductItemDesign(Material.CHEST);
    }

    public void togglePerk(User user, UserAsset perkAsset) {
        Player player = user.getPlayer();
        boolean activated = perkAsset.isActivated();
        if(!activated && user.getActivatedAssetsSize(Perk.class) >= MAXIMUM_PERKS_SELECTION) {
            ShopMessageKeys.PERK_SLOTS_MAXED_MESSAGE.sendMessage(player);
            return;
        }

        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);

        ShopMessageKeys toggleMessage = ShopMessageKeys.PERK_EQUIPPED_MESSAGE;
        if(activated) {
            toggleMessage = ShopMessageKeys.PERK_UN_EQUIPPED_MESSAGE;
        }
        perkAsset.toggleAsset();
        toggleMessage.sendMessage(player, new Replacement("name", perkAsset.getName()));
    }
}
