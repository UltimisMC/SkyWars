package xyz.directplan.directlib.shop;

/**
 * @author DirectPlan
 */
public abstract class ConfirmableProduct<U> extends AbstractProduct<U> {

    public ConfirmableProduct(ProductType type, String name, int itemSlot, boolean enabled) {
        super(type, name, itemSlot, enabled);
    }

    public ConfirmableProduct(String name, int itemSlot, boolean enabled) {
        super(name, itemSlot, enabled);
    }

    public ConfirmableProduct(String name, int itemSlot) {
        super(name, itemSlot);
    }
}
