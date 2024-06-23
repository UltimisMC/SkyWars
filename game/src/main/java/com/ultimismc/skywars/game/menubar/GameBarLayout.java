package com.ultimismc.skywars.game.menubar;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.ActionableItem;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PlayerInventoryLayout;

/**
 * @author DirectPlan
 */
public abstract class GameBarLayout extends PlayerInventoryLayout {

    protected final SkyWarsPlugin plugin;
    protected final GameHandler gameHandler;
    protected final User user;

    public GameBarLayout(SkyWarsPlugin plugin, GameHandler gameHandler, User user) {
        this.plugin = plugin;
        this.gameHandler = gameHandler;
        this.user = user;
    }

    @Override
    public void build(Player player) {
        MenuItem returnLobby = addBarItem(8, Material.BED, "&c&lReturn to Lobby");
        SkyWarsServerManager serverManager = plugin.getServerManager();
        returnLobby.setAction((item, clicker, clickedBlock, clickType) -> serverManager.sendToLobby(user));
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
