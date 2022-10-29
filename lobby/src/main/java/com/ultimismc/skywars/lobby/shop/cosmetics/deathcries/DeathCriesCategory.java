package com.ultimismc.skywars.lobby.shop.cosmetics.deathcries;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class DeathCriesCategory extends UserProductCategory {

    public DeathCriesCategory(int itemSlot) {
        super("Death Cries", itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        List<String> lore = ShopMessageKeys.COSMETIC_DEATH_CRIES_LORE.getStringList();
        return new ProductItemDesign(Material.GHAST_TEAR, lore);
    }
}
