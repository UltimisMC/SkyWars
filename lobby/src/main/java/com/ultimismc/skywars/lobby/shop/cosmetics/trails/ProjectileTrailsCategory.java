package com.ultimismc.skywars.lobby.shop.cosmetics.trails;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.projectiletrails.ProjectileTrailHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import com.ultimismc.skywars.lobby.shop.cosmetics.AbstractCosmeticCategory;
import lombok.Getter;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public class ProjectileTrailsCategory extends AbstractCosmeticCategory<ProjectileTrailHandler> {

    private final PurchasableDesign design = new PurchasableDesign(Material.ARROW);

    public ProjectileTrailsCategory(ProjectileTrailHandler cosmeticRegistry, int itemSlot) {
        super(cosmeticRegistry, ShopMessageKeys.COSMETIC_TRAILS_LORE.getStringList(), itemSlot);
    }
}
