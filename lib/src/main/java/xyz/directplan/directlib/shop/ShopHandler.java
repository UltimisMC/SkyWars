package xyz.directplan.directlib.shop;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.PaginatedModel;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.shop.menu.PaginatedProductCategoryMenu;
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

    public void openProductCategory(Player player, U user, InventoryUI previousMenu, ProductCategory<U> productCategory) {
        InventoryUI productCategoryMenu;
        if(productCategory.isPaginated()) {
            productCategoryMenu = new PaginatedProductCategoryMenu<>(this, user, previousMenu, productCategory, PaginatedModel.HYPIXEL_MODEL);
        }else {
            productCategoryMenu = new ProductCategoryMenu<>(this, user, previousMenu, productCategory);
        }
        openInventory(player, productCategoryMenu);
    }

    public void openProductCategory(Player player, U user, ProductCategory<U> productCategory) {
        openProductCategory(player, user, null, productCategory);
    }

    public ProductCategory<U> buildCategory(ProductCategoryBuilder<U> productCategoryBuilder) {
        return productCategoryBuilder.buildCategory();
    }
}
