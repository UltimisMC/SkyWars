package com.ultimismc.skywars.lobby.shop.level;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.level.LevelManager;
import com.ultimismc.skywars.core.game.features.level.Prestige;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.LobbyManager;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.level.icon.PrestigeIconProduct;
import com.ultimismc.skywars.lobby.shop.level.icon.PrestigeIconsCategory;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LevelProductCategoryBuilder implements UserProductCategoryBuilder {

    private final LobbyManager lobbyManager;
    private final FeatureHandler featureHandler;

    @Override
    public ProductCategory<User> buildCategory() {
        LevelManager levelManager = featureHandler.getLevelManager();

        LevelProductCategory productCategory = new LevelProductCategory(lobbyManager,16);

        productCategory.addProduct(new LevelDisplayProduct(10));
        int meterIndex = 1;
        for(int slot = 11; slot < 16; slot++) {
            productCategory.addProduct(new LevelMeterProduct(lobbyManager, meterIndex, slot));
            meterIndex++;
        }
        productCategory.addProduct(new NextLevelDisplayProduct(lobbyManager, 16));

        PrestigeIconsCategory prestigeIconsCategory = new PrestigeIconsCategory(31);
        for(Prestige prestige : levelManager.getPrestiges()) {
            prestigeIconsCategory.addProduct(new PrestigeIconProduct(prestige));
        }
        productCategory.addProduct(prestigeIconsCategory);

        productCategory.addProduct(new HideLevelProduct(50));
        return productCategory;
    }
}
