package xyz.directplan.directlib.shop;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.shop.menu.ProductCategoryMenu;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ShopHandler<U> {

    private final MenuManager menuManager;

    public void openProductCategory(Player player, U user, ProductCategory<U> productCategory) {
        menuManager.openInventory(player, new ProductCategoryMenu<>(this, user, productCategory));
    }

    public ProductCategory<U> buildCategory(ProductCategoryBuilder<U> productCategoryBuilder) {
        return productCategoryBuilder.buildCategory();
    }
}
