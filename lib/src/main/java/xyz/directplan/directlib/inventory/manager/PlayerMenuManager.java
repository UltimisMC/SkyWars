package xyz.directplan.directlib.inventory.manager;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import xyz.directplan.directlib.inventory.PlayerInventoryLayout;
import xyz.directplan.directlib.inventory.UserInventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
public class PlayerMenuManager {

    private final Map<UUID, CachedUserInventory> previousInventories = new HashMap<>();

    public void applyInventoryLayout(UserInventory userInventory, PlayerInventoryLayout inventoryLayout) {
        inventoryLayout.applyLayout(userInventory);

        userInventory.setInventoryLayout(inventoryLayout);
    }

    public void applyInventoryLayout(UserInventory userInventory, PlayerInventoryLayout inventoryLayout, boolean clearInventory, boolean cacheForLater) {
        Player player = userInventory.getPlayer();
        PlayerInventory playerInventory = player.getInventory();
        if(cacheForLater) {
            previousInventories.put(player.getUniqueId(),
                    new CachedUserInventory(
                            playerInventory.getContents(),
                            playerInventory.getArmorContents(),
                            userInventory.getInventoryLayout()));
        }
        if(clearInventory) {
            playerInventory.clear();
        }
        applyInventoryLayout(userInventory, inventoryLayout);
    }

    public void revertInventory(UserInventory user) {
        Player player = user.getPlayer();
        UUID uuid = player.getUniqueId();
        CachedUserInventory cachedUserInventory = previousInventories.get(uuid);
        if(cachedUserInventory == null) return;

        PlayerInventory inventory = player.getInventory();
        inventory.clear();

        inventory.setContents(cachedUserInventory.getContents());
        inventory.setArmorContents(cachedUserInventory.getArmorContents());
        PlayerInventoryLayout inventoryLayout = cachedUserInventory.getInventoryLayout();

        user.setInventoryLayout(inventoryLayout);

        player.updateInventory();
        previousInventories.remove(uuid);
        player.saveData();
    }
}

@Data
class CachedUserInventory {

    private final ItemStack[] contents, armorContents;
    private final PlayerInventoryLayout inventoryLayout;
}
