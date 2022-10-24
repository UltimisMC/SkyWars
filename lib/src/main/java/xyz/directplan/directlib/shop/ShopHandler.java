package xyz.directplan.directlib.shop;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.shop.menu.ProductCategoryMenu;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ShopHandler<U> {

    private final MenuManager menuManager;

    public void openInventory(Player player, InventoryUI inventoryUI) {
        menuManager.openInventory(player, inventoryUI);
    }

    public void openProductCategory(Player player, U user, ProductCategory<U> productCategory) {
        openInventory(player, new ProductCategoryMenu<>(this, user, productCategory));
    }

    public void openProductCategory(Player player, U user, InventoryUI previousMenu, ProductCategory<U> productCategory) {
        openInventory(player, new ProductCategoryMenu<>(this, user, previousMenu, productCategory));
    }

    public ProductCategory<U> buildCategory(ProductCategoryBuilder<U> productCategoryBuilder) {
        return productCategoryBuilder.buildCategory();
    }
}
