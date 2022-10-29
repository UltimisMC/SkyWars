package com.ultimismc.skywars.lobby.shop;

import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.shop.AbstractProductCategory;

/**
 * @author DirectPlan
 */
public abstract class UserProductCategory extends AbstractProductCategory<User> {

    public UserProductCategory(String name, int itemSlot, int inventoryRows, boolean paginated, boolean enabled) {
        super(name, itemSlot, inventoryRows, paginated, enabled);
    }

    public UserProductCategory(String name, int itemSlot, int inventoryRows, boolean paginated) {
        super(name, itemSlot, inventoryRows, paginated);
    }

    public UserProductCategory(String name, int itemSlot, boolean paginated) {
        super(name, itemSlot, 6, paginated);
    }

    public UserProductCategory(String name, int itemSlot, int inventoryRows) {
        this(name, itemSlot, inventoryRows, false);
    }

    public UserProductCategory(String name, int itemSlot) {
        this(name, itemSlot, 6);
    }

    @Override
    public void executeAction(User user) {}
}
