package com.ultimismc.skywars.game.menubar;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.menubar.menu.SpectatorListMenu;
import com.ultimismc.skywars.game.menubar.menu.SpectatorSettingsMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.MenuItem;

/**
 * @author DirectPlan
 */
public class GameSpectatorBarMenu extends GameBarMenu {

    public GameSpectatorBarMenu(GameHandler gameHandler, User user) {
        super(gameHandler, user);
    }

    @Override
    public void build(Player player) {
        super.build(player);

        MenuItem teleporterItem = addBarItem(0, Material.COMPASS, "&a&lTeleporter", (item, clicker, clickedBlock, clickType) -> gameHandler.openInventory(clicker, new SpectatorListMenu(gameHandler)));
        teleporterItem.setLore("&7Right-click to spectate players!");

        MenuItem spectatorSettingsItem = addBarItem(4, Material.REDSTONE_COMPARATOR, "&b&lSpectator Settings", (item, clicker, clickedBlock, clickType) -> gameHandler.openInventory(clicker, new SpectatorSettingsMenu(user)));
        spectatorSettingsItem.setLore("&7Right-click to change your spectator settings!");

        MenuItem playAgainItem = addBarItem(7, Material.PAPER, "&b&lPlay Again", (item, clicker, clickedBlock, clickType) -> {

            // Sending to another server!
            user.sendMessage("&aSending to a non-existent game...");
            user.sendMessage("&eThis is the only game for now you idiot!");
        });
        playAgainItem.setLore("&7Right-click to play another game!");
    }
}
