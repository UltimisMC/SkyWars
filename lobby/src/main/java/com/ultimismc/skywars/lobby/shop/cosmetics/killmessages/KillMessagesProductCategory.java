package com.ultimismc.skywars.lobby.shop.cosmetics.killmessages;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class KillMessagesProductCategory extends UserProductCategory {

    public KillMessagesProductCategory(int itemSlot) {
        super("Kill Messages", itemSlot, true);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        List<String> lore = ShopMessageKeys.COSMETIC_KILL_MESSAGES_LORE.getStringList();
        return new ProductItemDesign(Material.SIGN, lore);
    }
}
