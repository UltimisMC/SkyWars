package com.ultimismc.skywars.lobby.shop.cosmetics.trails;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class ProjectileTrailsCategory extends UserProductCategory {

    public ProjectileTrailsCategory(int itemSlot) {
        super("Projectile Trails", itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        List<String> lore = ShopMessageKeys.COSMETIC_TRAILS_LORE.getStringList();
        return new ProductItemDesign(Material.ARROW, lore);
    }
}
