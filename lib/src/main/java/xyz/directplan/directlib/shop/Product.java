package xyz.directplan.directlib.shop;

import org.bukkit.event.inventory.ClickType;

/**
 * @author DirectPlan
 */
public interface Product<U> {

    ProductType getType();

    String getName();

    int getItemSlot();

    int getInventoryRows();

    boolean isDisplay();

    boolean isEnabled();

    boolean isCategory();

    ProductCategory<U> getProductPath();

    ProductItemDesign designProduct(U user);

    void executeAction(U user, ClickType clickType);

    default void onRightClick(U user) {}

    default boolean hasRightClickSupport() { return false; }

    default boolean isRefreshInventoryEnabled() { return false; }
}
