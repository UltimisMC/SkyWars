package xyz.directplan.directlib.inventory;

import org.bukkit.entity.Player;

public interface UserInventory {

    Player getPlayer();

    PlayerInventoryLayout getInventoryLayout();

    void setInventoryLayout(PlayerInventoryLayout inventoryLayout);
}
