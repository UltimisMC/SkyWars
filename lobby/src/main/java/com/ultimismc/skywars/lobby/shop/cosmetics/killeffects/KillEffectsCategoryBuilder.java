package com.ultimismc.skywars.lobby.shop.cosmetics.killeffects;

import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffect;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffectHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.cosmetics.CosmeticPurchasableProduct;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class KillEffectsCategoryBuilder implements UserProductCategoryBuilder {

    private final CosmeticManager cosmeticManager;

    @Override
    public ProductCategory<User> buildCategory() {
        KillEffectHandler killEffectHandler = cosmeticManager.getKillEffectHandler();

        KillEffectsCategory category = new KillEffectsCategory(killEffectHandler, 16);
        for(KillEffect killEffect : killEffectHandler) {
            category.addProduct(new CosmeticPurchasableProduct(killEffectHandler, killEffect));
        }
        return category;
    }
}
