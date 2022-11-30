package com.ultimismc.skywars.lobby.shop.cosmetics.killeffects;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffectHandler;
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
public class KillEffectsCategory extends AbstractCosmeticCategory<KillEffectHandler> {

    private final PurchasableDesign design = new PurchasableDesign(Material.BONE);

    public KillEffectsCategory(KillEffectHandler cosmeticRegistry, int itemSlot) {
        super(cosmeticRegistry, ShopMessageKeys.COSMETIC_KILL_EFFECTS_LORE.getStringList(), itemSlot);
    }
}
