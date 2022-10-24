package xyz.directplan.directlib.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;
import xyz.directplan.directlib.inventory.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractProductCategory<U> extends AbstractProduct<U> implements ProductCategory<U> {

    private final int inventoryRows;

    private final List<Product<U>> products = new ArrayList<>();

    public AbstractProductCategory(String name, int itemSlot, int inventoryRows, boolean enabled) {
        super(ProductType.CATEGORY, name, itemSlot, enabled);
        this.inventoryRows = inventoryRows;
    }

    public AbstractProductCategory(String name, int itemSlot, int inventoryRows) {
        this(name, itemSlot, inventoryRows, true);
    }

    public abstract ProductItemDesign designCategory(U user);

    @Override
    public ProductItemDesign designItem(U user) {
        return designCategory(user);
    }

    public void addProduct(Product<U> product) {
        products.add(product);
    }
}
