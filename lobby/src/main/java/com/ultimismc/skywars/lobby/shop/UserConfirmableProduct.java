package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.shop.ConfirmableProduct;
import xyz.directplan.directlib.shop.ProductType;

/**
 * @author DirectPlan
 */
public abstract class UserConfirmableProduct extends ConfirmableProduct<User> {

    public UserConfirmableProduct(ProductType type, String name, int itemSlot, int inventoryRows, boolean enabled) {
        super(type, name, itemSlot, inventoryRows, enabled);
    }

    public UserConfirmableProduct(String name, int itemSlot, int inventoryRows, boolean enabled) {
        super(name, itemSlot, inventoryRows, enabled);
    }

    public UserConfirmableProduct(String name, int itemSlot, int inventoryRows) {
        super(name, itemSlot, inventoryRows);
    }

    public UserConfirmableProduct(String name, int itemSlot) {
        super(name, itemSlot);
    }
}
