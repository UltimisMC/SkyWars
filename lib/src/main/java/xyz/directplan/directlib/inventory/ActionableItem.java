package xyz.directplan.directlib.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/**
 * @author DirectPlan
 */
public interface ActionableItem {

    void performAction(MenuItem item, Player clicker, ClickType clickType);
}
