package xyz.directplan.directlib.inventory.manager;

import lombok.Getter;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.InventoryUI;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author AbderrahimLach
 */
public class MenuManager extends PlayerMenuManager {

    @Getter private final Map<UUID, InventoryUI> inventories = new HashMap<>();
    
    public InventoryUI getInventory(UUID uuid){
        return inventories.get(uuid);
    }

    public InventoryUI removeInventory(UUID uuid){
        return this.inventories.remove(uuid);
    }

    public void addInventory(UUID uuid, InventoryUI inventoryUI){
        inventories.put(uuid, inventoryUI);
    }

    public void openInventory(Player player, InventoryUI inventoryUI) {
        inventoryUI.open(player);
        addInventory(player.getUniqueId(), inventoryUI);
    }
}
