package xyz.directplan.directlib.shop;

import java.util.List;

/**
 * @author DirectPlan
 */
public interface ProductCategory<U> extends Product<U> {

    List<Product<U>> getProducts();

    boolean isPaginated();
}
