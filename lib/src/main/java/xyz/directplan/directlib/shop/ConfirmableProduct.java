package xyz.directplan.directlib.shop;

/**
 * @author DirectPlan
 */
public abstract class ConfirmableProduct<U> extends AbstractProduct<U> {

    public ConfirmableProduct(ProductType type, String name, int inventoryRows, int itemSlot, boolean enabled) {
        super(type, name, itemSlot, inventoryRows, enabled);
    }

    public ConfirmableProduct(String name, int itemSlot, int inventoryRows, boolean enabled) {
        super(name, itemSlot, inventoryRows, enabled);
    }

    public ConfirmableProduct(String name, int itemSlot) {
        super(name, itemSlot);
    }

    public ConfirmableProduct(String name, int itemSlot, int inventoryRows) {
        super(name, itemSlot, inventoryRows);
    }
}
