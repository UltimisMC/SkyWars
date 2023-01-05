package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.shop.ProductType;
import xyz.directplan.directlib.shop.TypedProduct;

/**
 * @author DirectPlan
 */
public abstract class UserTypedProduct<T> extends TypedProduct<User, T> {

    public UserTypedProduct(ProductType type, String name, int itemSlot, int inventoryRows, boolean enabled, Class<T> cast) {
        super(type, name, itemSlot, inventoryRows, enabled, cast);
    }

    public UserTypedProduct(String name, int itemSlot, Class<T> cast) {
        super(name, itemSlot, cast);
    }
}
