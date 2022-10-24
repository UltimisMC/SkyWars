package xyz.directplan.directlib.shop.menu;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.inventory.ActionableItem;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.shop.Product;
import xyz.directplan.directlib.shop.ProductCategory;
import xyz.directplan.directlib.shop.ShopHandler;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ProductCategoryAction<U> implements ActionableItem {

    private final ShopHandler<U> shopHandler;

    private final U user;
    private final InventoryUI currentMenu;
    private final Product<U> product;

    @Override
    public void performAction(MenuItem item, Player clicker, ClickType clickType) {
        if(product.isDisplay()) return;
        if(product.isCategory()) {
            ProductCategory<U> productCategory = (ProductCategory<U>) product;
            shopHandler.openProductCategory(clicker, user, currentMenu, productCategory);
            return;
        }

        product.executeAction(user);
    }
}
