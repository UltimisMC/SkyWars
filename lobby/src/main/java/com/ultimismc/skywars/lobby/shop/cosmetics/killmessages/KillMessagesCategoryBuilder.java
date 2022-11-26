package com.ultimismc.skywars.lobby.shop.cosmetics.killmessages;

import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessage;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.cosmetics.CosmeticPurchasableProduct;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class KillMessagesCategoryBuilder implements UserProductCategoryBuilder {

    private final CosmeticManager cosmeticManager;

    @Override
    public ProductCategory<User> buildCategory() {

        KillMessageHandler killMessageHandler = cosmeticManager.getKillMessageHandler();

        KillMessagesProductCategory productCategory = new KillMessagesProductCategory(32);
        for(KillMessage killMessage : killMessageHandler) {
            productCategory.addProduct(new CosmeticPurchasableProduct(killMessage));
        }
        return productCategory;
    }
}
