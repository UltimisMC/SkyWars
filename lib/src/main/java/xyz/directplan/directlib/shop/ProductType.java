package xyz.directplan.directlib.shop;

/**
 * @author DirectPlan
 */
public enum ProductType {

    CATEGORY,
    PRODUCT,
    ;

    public boolean isCategory() {
        return this == CATEGORY;
    }
}
