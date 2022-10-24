package xyz.directplan.directlib.inventory;

import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
public interface InventoryUser<T extends PlayerInventoryUI> {

    Player getPlayer();

    T getInventoryUi();

    void setCurrentInventoryUi(T inventoryUI);
}
