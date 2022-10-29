package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.shop.ConfirmableProduct;
import xyz.directplan.directlib.shop.ProductType;

/**
 * @author DirectPlan
 */
public abstract class UserConfirmableProduct extends ConfirmableProduct<User> {

    public UserConfirmableProduct(ProductType type, String name, int itemSlot, boolean enabled) {
        super(type, name, itemSlot, enabled);
    }

    public UserConfirmableProduct(String name, int itemSlot, boolean enabled) {
        super(name, itemSlot, enabled);
    }

    public UserConfirmableProduct(String name, int itemSlot) {
        super(name, itemSlot);
    }
}
