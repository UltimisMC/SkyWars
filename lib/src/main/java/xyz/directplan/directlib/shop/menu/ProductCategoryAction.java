package xyz.directplan.directlib.shop.menu;

import lombok.RequiredArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.inventory.ActionableItem;
import xyz.directplan.directlib.inventory.ConfirmableActionMenu;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.shop.*;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ProductCategoryAction<U> implements ActionableItem {

    private final ShopHandler<U> shopHandler;

    private final U user;
    private final Product<U> product;
    private final ProductItemDesign itemDesign;
    private final InventoryUI currentMenu;
    private final boolean ignoreConfirmation;
    private final boolean canAfford;

    @Override
    public void performAction(MenuItem item, Player clicker, Block clickedBlock, ClickType clickType) {
        if(product.isDisplay()) return;
        if(product.hasRightClickSupport() && clickType == ClickType.RIGHT) {
            product.onRightClick(user);
            return;
        }
        if(!canAfford) return;
        if(product.isCategory()) {
            ProductCategory<U> productCategory = (ProductCategory<U>) product;
            shopHandler.openProductCategory(clicker, user, currentMenu, productCategory);
            return;
        }

        ProductCategory<U> productPath = product.getProductPath();
        if(productPath != null) {
            shopHandler.openProductCategory(clicker, user, currentMenu, productPath);
            return;
        }

        if(!ignoreConfirmation && product instanceof ConfirmableProduct) {
            shopHandler.openInventory(clicker, new ConfirmableActionMenu(item, () -> product.executeAction(user, clickType)));
            return;
        }
        Object data = itemDesign.getData();
        if(data != null && product instanceof TypedProduct) {
            TypedProduct<U, ?> typedProduct = (TypedProduct<U, ?>) product;
            typedProduct.executeActionObjectData(user, data, clickType);
            return;
        }
        product.executeAction(user, clickType);
    }

    @Override
    public boolean updateButtons(Player clicker, ClickType clickType) {
        return product.isRefreshInventoryEnabled();
    }
}
