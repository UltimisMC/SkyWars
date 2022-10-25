package com.ultimismc.skywars.lobby.shop.kitsandperks.kits;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class KitsProductCategory extends UserProductCategory {

    public KitsProductCategory(String name, int itemSlot) {
        super(name, itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        return null;
    }
}
