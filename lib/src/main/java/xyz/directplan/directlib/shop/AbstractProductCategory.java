package xyz.directplan.directlib.shop;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractProductCategory<U> extends AbstractProduct<U> implements ProductCategory<U> {

    private final int inventoryRows;
    private final boolean paginated;
    private final List<Product<U>> products = new ArrayList<>();

    public AbstractProductCategory(String name, int itemSlot, int inventoryRows, boolean paginated, boolean enabled) {
        super(ProductType.CATEGORY, name, itemSlot, enabled);
        this.inventoryRows = inventoryRows;
        this.paginated = paginated;
    }

    public AbstractProductCategory(String name, int itemSlot, int inventoryRows, boolean paginated) {
        this(name, itemSlot, inventoryRows, paginated, true);
    }

    public AbstractProductCategory(String name, int itemSlot, int inventoryRows) {
        this(name, itemSlot, inventoryRows, true);
    }

    public abstract ProductItemDesign designCategory(U user);

    @Override
    public ProductItemDesign designProduct(U user) {
        return designCategory(user);
    }

    public void addProduct(Product<U> product) {
        products.add(product);
    }

    public void buildProductCategory(ProductCategoryBuilder<U> productCategoryBuilder) {
        ProductCategory<U> productCategory = productCategoryBuilder.buildCategory();
        addProduct(productCategory);
    }
}
