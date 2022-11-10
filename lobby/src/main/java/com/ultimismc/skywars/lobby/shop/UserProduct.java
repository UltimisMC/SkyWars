package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.shop.AbstractProduct;


/**
 * @author DirectPlan
 */
public abstract class UserProduct extends AbstractProduct<User> {

    public UserProduct(String name, int inventoryRows, int itemSlot) {
        super(name, inventoryRows, itemSlot);
    }

    public UserProduct(String name, int itemSlot) {
        super(name, itemSlot);
    }
}
