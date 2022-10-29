package com.ultimismc.skywars.lobby.shop.cosmetics.sprays;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class SpraysCategory extends UserProductCategory {

    public SpraysCategory(int itemSlot) {
        super("Sprays", itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        List<String> lore = ShopMessageKeys.COSMETIC_SPRAY_LORE.getStringList();
        return new ProductItemDesign(Material.PAINTING, lore);
    }
}
