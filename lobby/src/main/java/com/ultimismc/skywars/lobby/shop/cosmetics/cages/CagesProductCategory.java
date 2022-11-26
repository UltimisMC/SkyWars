package com.ultimismc.skywars.lobby.shop.cosmetics.cages;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class CagesProductCategory extends UserProductCategory {

    public CagesProductCategory(int itemSlot) {
        super("Cages", itemSlot, true);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        List<String> lore = ShopMessageKeys.COSMETIC_CAGES_LORE.getStringList();
        return new ProductItemDesign(Material.STAINED_GLASS, lore);
    }
}
