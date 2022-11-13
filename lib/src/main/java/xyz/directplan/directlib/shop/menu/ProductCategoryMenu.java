package xyz.directplan.directlib.shop.menu;

import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.shop.Product;
import xyz.directplan.directlib.shop.ProductCategory;
import xyz.directplan.directlib.shop.ShopHandler;

import java.util.List;

/**
 * @author DirectPlan
 */
public class ProductCategoryMenu<U> extends InventoryUI {

    private final ProductCategory<U> productCategory;
    private final InventoryUI previousMenu;
    private final ProductMenuBuilder<U> productMenuBuilder;

    public ProductCategoryMenu(ShopHandler<U> shopHandler, U user, InventoryUI previousMenu, ProductCategory<U> productCategory) {
        super(productCategory.getName(), productCategory.getInventoryRows());

        this.productCategory = productCategory;
        this.previousMenu = previousMenu;

        productMenuBuilder = new ProductMenuBuilder<>(shopHandler, user, this);
    }

    public ProductCategoryMenu(ShopHandler<U> shopHandler, U user, ProductCategory<U> productCategory) {
        this(shopHandler, user, null, productCategory);
    }

    @Override
    public void build(Player player) {
        List<Product<U>> products = productCategory.getProducts();
        for(Product<U> product : products) {
            MenuItem menuItem = productMenuBuilder.buildProduct(product);
            if(menuItem == null) continue;
            setSlot(product.getItemSlot(), menuItem);
        }
        if(previousMenu != null) {
            productMenuBuilder.addGoBackButton(player, this, previousMenu);
            return;
        }
        addCloseButton();
    }

    @Override
    public String getInventoryId() {
        return productCategory.getName();
    }
}

