package com.ultimismc.skywars.game.menubar;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserPlayerInventoryUi;
import com.ultimismc.skywars.game.handler.GameHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.ActionableItem;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;

/**
 * @author DirectPlan
 */
public class GameBarMenu extends UserPlayerInventoryUi {

    protected final GameHandler gameHandler;

    public GameBarMenu(GameHandler gameHandler, User user) {
        super(user);
        this.gameHandler = gameHandler;
    }

    @Override
    public void build(Player player) {
        MenuItem returnLobby = addBarItem(8, Material.BED, "&c&lReturn to Lobby");
        returnLobby.setLore("&7Right-click to leave to the lobby!");
    }

    public MenuItem addBarItem(int slot, Material material, String displayName, ActionableItem action) {
        MenuItem menuItem = new MenuItem(material, displayName + " &7(Right Click)", action);
        setSlot(slot, menuItem);
        return menuItem;
    }

    public MenuItem addBarItem(int slot, Material material, String displayName) {
        return addBarItem(slot, material, displayName, (item, clicker, clickedBlock, clickType) ->
                clicker.sendMessage(PluginUtility.translateMessage("&cStill under construction!")));
    }

    public MenuItem addBarItem(int slot, Material material, String displayName, InventoryUI inventoryUI) {
        return addBarItem(slot, material, displayName, (item, clicker, clickedBlock, clickType) -> gameHandler.openInventory(clicker, inventoryUI));
    }
}
