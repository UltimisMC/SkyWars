package com.ultimismc.skywars.lobby.shop.kitsandperks.kits;

import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class KitProductCategory extends UserProductCategory {

    private final Kit kit;

    public KitProductCategory(Kit kit) {
        super(kit.getName(), 0);
        this.kit = kit;
    }

    @Override
    public ProductItemDesign designCategory(User user) {


        return null;
    }
}
