package xyz.directplan.directlib.inventory.manager;

import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.UserInventory;

/**
 * @author DirectPlan
 */
public interface UserInventoryProvider {

    UserInventory getUser(Player player);
}
