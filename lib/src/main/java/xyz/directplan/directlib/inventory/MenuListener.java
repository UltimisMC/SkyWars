package xyz.directplan.directlib.inventory;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.directplan.directlib.inventory.manager.MenuManager;

import java.util.UUID;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class MenuListener implements Listener {

    private final MenuManager menuManager;

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        // Player Currently Opened UI
        InventoryUI inventoryUI = menuManager.getInventory(player.getUniqueId());
        if(inventoryUI != null){
            inventoryUI.onClick(event);
            return;
        }

        // Player Inventory UI
        InventoryUser inventoryUser = menuManager.getInventoryUser(player);
        if(inventoryUser != null) {
            PlayerInventoryUI playerInventoryUI = inventoryUser.getInventoryUi();
            playerInventoryUI.onClick(event);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        InventoryUser inventoryUser = menuManager.getInventoryUser(player);
        if(inventoryUser != null) {
            PlayerInventoryUI playerInventoryUI = inventoryUser.getInventoryUi();
            playerInventoryUI.onInteract(event);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        UUID uuid = player.getUniqueId();
        InventoryUI inventoryUI = menuManager.getInventory(player.getUniqueId());
        if(inventoryUI != null && !inventoryUI.isLocked()) {
            inventoryUI.onClose(event.getInventory());
            menuManager.removeInventory(uuid);
        }
    }
}
