package xyz.directplan.directlib.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public abstract class AbstractProduct<U> implements Product<U> {

    private final ProductType type;

    private final String name;
    private final int itemSlot;
    private final int inventoryRows;
    private final boolean enabled;
    @Setter private ProductCategory<U> productPath;

    public AbstractProduct(ProductType productType, String name, int itemSlot, boolean enabled) {
        this(productType, name, itemSlot, 6, enabled); // PRODUCT DEFAULT ROWS
    }

    public AbstractProduct(String name, int itemSlot, int inventoryRows, boolean enabled) {
        this(ProductType.PRODUCT, name, itemSlot, inventoryRows, enabled);
    }

    public AbstractProduct(String name, int itemSlot, boolean enabled) {
        this(name, itemSlot, 6, enabled); // PRODUCT DEFAULT ROWS
    }
    public AbstractProduct(String name, int inventoryRows, int itemSlot) {
        this(name, itemSlot, inventoryRows,true);
    }

    public AbstractProduct(String name, int itemSlot) {
        this(name, itemSlot, 6, true); // PRODUCT DEFAULT ROWS
    }

    @Override
    public boolean isCategory() {
        return type.isCategory();
    }

    @Override
    public boolean isDisplay() {
        return false;
    }
}
