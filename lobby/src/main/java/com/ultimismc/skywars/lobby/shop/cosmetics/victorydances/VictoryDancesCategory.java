package com.ultimismc.skywars.lobby.shop.cosmetics.victorydances;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class VictoryDancesCategory extends UserProductCategory {

    public VictoryDancesCategory(int itemSlot) {
        super("Victory Dances", itemSlot);
    }

    // TODO: ADD AN ABSTRACT CLASS SO THAT YOU COULD RECTIFY THE STATS LIKE UNLOCKED/TOTAL AND SELECTED

    @Override
    public ProductItemDesign designCategory(User user) {
        List<String> lore = ShopMessageKeys.COSMETIC_VICTORY_DANCES_LORE.getStringList();
        return new ProductItemDesign(Material.SKULL_ITEM, lore);
    }
}
