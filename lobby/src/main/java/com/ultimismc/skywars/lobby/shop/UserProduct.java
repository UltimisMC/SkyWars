package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.lobby.user.User;
import xyz.directplan.directlib.shop.AbstractProduct;


/**
 * @author DirectPlan
 */
public abstract class UserProduct extends AbstractProduct<User> {

    public UserProduct(String name, int itemSlot) {
        super(name, itemSlot);
    }
}
