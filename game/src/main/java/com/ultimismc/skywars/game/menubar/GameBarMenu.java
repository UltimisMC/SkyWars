package com.ultimismc.skywars.game.menubar;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserPlayerInventoryUi;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.ActionableItem;
import xyz.directplan.directlib.inventory.MenuItem;

/**
 * @author DirectPlan
 */
public class GameBarMenu extends UserPlayerInventoryUi {

    public GameBarMenu(User user) {
        super(user);
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
        return addBarItem(slot, material, displayName, null);
    }
}
