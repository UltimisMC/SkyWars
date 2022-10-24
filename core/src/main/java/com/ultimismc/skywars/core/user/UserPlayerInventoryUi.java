package com.ultimismc.skywars.core.user;

import xyz.directplan.directlib.inventory.PlayerInventoryUI;

/**
 * @author DirectPlan
 */
public abstract class UserPlayerInventoryUi extends PlayerInventoryUI<User> {

    public UserPlayerInventoryUi(User user) {
        super(user);
    }
}
