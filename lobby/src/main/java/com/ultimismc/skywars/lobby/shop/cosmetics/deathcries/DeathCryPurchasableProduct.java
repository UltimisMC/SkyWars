package com.ultimismc.skywars.lobby.shop.cosmetics.deathcries;

import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCry;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.cosmetics.CosmeticPurchasableProduct;

/**
 * @author DirectPlan
 */
public class DeathCryPurchasableProduct extends CosmeticPurchasableProduct {

    public DeathCryPurchasableProduct(PurchasableRegistry<?> registry, Cosmetic cosmetic) {
        super(registry, cosmetic, true);
    }

    @Override
    public void onRightClick(User user) {
        DeathCry deathCry = (DeathCry) cosmetic;
        deathCry.playDeathCry(user);
    }
}
