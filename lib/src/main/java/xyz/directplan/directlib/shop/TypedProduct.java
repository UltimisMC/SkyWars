package xyz.directplan.directlib.shop;

import org.bukkit.event.inventory.ClickType;

/**
 * @author DirectPlan
 */
public abstract class TypedProduct<U, T> extends AbstractProduct<U> {

    private final Class<T> cast;
    public TypedProduct(ProductType type, String name, int itemSlot, int inventoryRows, boolean enabled, Class<T> cast) {
        super(type, name, itemSlot, inventoryRows, enabled);
        this.cast = cast;
    }

    public TypedProduct(String name, int itemSlot, Class<T> cast) {
        super(name, itemSlot);
        this.cast = cast;
    }



    public abstract void executeAction(U user, T obj, ClickType clickType);

    public void executeActionObjectData(U user, Object data, ClickType clickType) {
        executeAction(user, cast.cast(data), clickType);
    }

    @Override
    public void executeAction(U user, ClickType clickType) {}
}
