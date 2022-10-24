package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.shop.AbstractProductCategory;

/**
 * @author DirectPlan
 */
public abstract class UserProductCategory extends AbstractProductCategory<User> {


    public UserProductCategory(String name, int itemSlot, int inventoryRows) {
        super(name, itemSlot, inventoryRows);
    }

    public UserProductCategory(String name, int itemSlot) {
        this(name, itemSlot, 6);
    }

    @Override
    public void executeAction(User user) {}
}
