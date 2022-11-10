package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class SoulHarvestersCategory extends UserProductCategory {

    public SoulHarvestersCategory(int itemSlot) {
        super("Soul Harvesters", itemSlot, 4);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        return new ProductItemDesign(Material.PAPER,
                Arrays.asList("&7Use &6coins &7to hire harvesters",
                        "&7to gather &bSouls &7for you."));
    }
}
