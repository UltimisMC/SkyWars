package com.ultimismc.skywars.lobby.shop.cosmetics.ballons;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class BallonsCategory extends UserProductCategory {

    public BallonsCategory(int itemSlot) {
        super("Ballons", itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        List<String> lore = ShopMessageKeys.COSMETIC_BALLONS_LORE.getStringList();
        return new ProductItemDesign(Material.STRING, lore);
    }
}
