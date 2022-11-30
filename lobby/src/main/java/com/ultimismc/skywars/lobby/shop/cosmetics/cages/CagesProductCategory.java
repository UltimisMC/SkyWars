package com.ultimismc.skywars.lobby.shop.cosmetics.cages;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.CageHandler;
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
public class CagesProductCategory extends AbstractCosmeticCategory<CageHandler> {

    private final PurchasableDesign design = new PurchasableDesign(Material.STAINED_GLASS);

    public CagesProductCategory(CageHandler cosmeticRegistry, int itemSlot) {
        super(cosmeticRegistry, ShopMessageKeys.COSMETIC_CAGES_LORE.getStringList(), itemSlot);
    }
}
