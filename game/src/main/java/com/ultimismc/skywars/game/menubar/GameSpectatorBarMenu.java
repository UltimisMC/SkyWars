package com.ultimismc.skywars.game.menubar;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
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

        MenuItem teleporterItem = addBarItem(0, Material.COMPASS, "&a&lTeleporter");
        teleporterItem.setLore("&7Right-click to spectate players!");

        MenuItem spectatorSettingsItem = addBarItem(4, Material.REDSTONE_COMPARATOR, "&b&lSpectator Settings");
        spectatorSettingsItem.setLore("&7Right-click to change your spectator settings!");

        MenuItem playAgainItem = addBarItem(7, Material.PAPER, "&b&lPlay Again");
        playAgainItem.setLore("&7Righ-click to play another game!");
    }
}
