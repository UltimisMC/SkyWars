package com.ultimismc.skywars.lobby.shop.cosmetics.killeffects;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class KillEffectsCategory extends UserProductCategory {
    public KillEffectsCategory(int itemSlot) {
        super("Kill Effects", itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        List<String> lore = ShopMessageKeys.COSMETIC_KILL_EFFECTS_LORE.getStringList();
        return new ProductItemDesign(Material.BONE, lore);
    }
}
