package xyz.directplan.directlib.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.inventory.manager.UserInventoryProvider;

import java.util.UUID;

/**
 * @author DirectPlan
 */

public class MenuListener implements Listener {

    private final MenuManager menuManager;
    private final UserInventoryProvider userInventoryProvider;

    public MenuListener(MenuManager menuManager, UserInventoryProvider userInventoryProvider) {
        this.menuManager = menuManager;
        this.userInventoryProvider = userInventoryProvider;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        // Player Currently Opened UI
        InventoryUI inventoryUI = menuManager.getInventory(player.getUniqueId());
        if(inventoryUI != null){
            inventoryUI.onClick(menuManager, event);
            return;
        }

        // Player Inventory UI
        UserInventory userInventory = userInventoryProvider.getUser(player);
        PlayerInventoryLayout playerInventoryUI = userInventory.getInventoryLayout();

        if(playerInventoryUI == null) return;
        playerInventoryUI.onClick(menuManager, event);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        UserInventory userInventory = userInventoryProvider.getUser(player);
        PlayerInventoryLayout currentLayout = userInventory.getInventoryLayout();

        if(currentLayout == null) return;
        currentLayout.onInteract(menuManager, event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        UUID uuid = player.getUniqueId();
        InventoryUI inventoryUI = menuManager.getInventory(player.getUniqueId());
        if(inventoryUI == null || inventoryUI.isLocked()) return;

        inventoryUI.onClose(player, event.getInventory());
        menuManager.removeInventory(uuid);
    }
}
