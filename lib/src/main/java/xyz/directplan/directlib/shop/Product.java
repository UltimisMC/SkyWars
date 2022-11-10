package xyz.directplan.directlib.shop;


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

    void executeAction(U user);
}
