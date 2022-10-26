package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.lobby.LobbyManager;
import com.ultimismc.skywars.lobby.shop.main.MainProductCategoryBuilder;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.shop.ProductCategory;
import xyz.directplan.directlib.shop.ProductCategoryBuilder;
import xyz.directplan.directlib.shop.ShopHandler;

/**
 * @author DirectPlan
 */

public class SkyWarsShopHandler {

    private final ShopHandler<User> shopHandler;
    private final FeatureHandler featureHandler;

    private ShopProductCategory mainShopCategory;

    public SkyWarsShopHandler(SkyWarsPlugin plugin) {
        shopHandler = new ShopHandler<>(plugin.getMenuManager());
        featureHandler = plugin.getFeatureHandler();
    }

    public void initializeShop() {
        mainShopCategory = (ShopProductCategory) buildCategory(new MainProductCategoryBuilder(featureHandler));
    }

    public void openShopMenu(User user) {
        Player player = user.getPlayer();
        shopHandler.openProductCategory(player, user, mainShopCategory);
    }
    public ProductCategory<User> buildCategory(ProductCategoryBuilder<User> productCategoryBuilder) {
        return shopHandler.buildCategory(productCategoryBuilder);
    }
}
