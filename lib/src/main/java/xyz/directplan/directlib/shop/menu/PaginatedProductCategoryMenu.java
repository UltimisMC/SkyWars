package xyz.directplan.directlib.shop.menu;

import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.PaginatedMenu;
import xyz.directplan.directlib.inventory.PaginatedModel;
import xyz.directplan.directlib.shop.Product;
import xyz.directplan.directlib.shop.ProductCategory;
import xyz.directplan.directlib.shop.ShopHandler;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public abstract class PaginatedProductCategoryMenu<U> extends PaginatedMenu<Product<U>> {

    protected final ShopHandler<U> shopHandler;

    private final ProductCategory<U> productCategory;
    protected final InventoryUI previousMenu;
    protected final U user;

    public PaginatedProductCategoryMenu(ShopHandler<U> shopHandler, U user, InventoryUI previousMenu, ProductCategory<U> productCategory, PaginatedModel paginatedModel) {
        super(productCategory.getName(), productCategory.getInventoryRows(), paginatedModel);

        this.shopHandler = shopHandler;
        this.productCategory = productCategory;
        this.previousMenu = previousMenu;
        this.user = user;
    }

    public PaginatedProductCategoryMenu(ShopHandler<U> shopHandler, U user, ProductCategory<U> productCategory) {
        this(shopHandler, user, null, productCategory, PaginatedModel.DEFAULT_MODEL);
    }
    @Override
    public Collection<Product<U>> getList() {
        return productCategory.getProducts();
    }
}
