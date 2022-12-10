package com.ultimismc.skywars.game.menubar.menu;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PaginatedMenu;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public class SpectatorListMenu extends PaginatedMenu<UserGameSession> {

    private final GameHandler gameHandler;

    public SpectatorListMenu(GameHandler gameHandler) {
        super("Teleporter", -1);
        this.gameHandler = gameHandler;
    }

    @Override
    public Collection<UserGameSession> getList() {
        return gameHandler.getCurrentPlayers();
    }

    @Override
    public MenuItem buildContent(Player player, UserGameSession content) {
        User user = content.getUser();
        Player other = user.getPlayer();
        MenuItem menuItem = new MenuItem(Material.SKULL_ITEM, user.getDisplayName());
        menuItem.setCustomSkullName(user.getName());
        int healthPercentage = (int) ((other.getHealth() * 100.0) / 20.0);
        int foodPercentage = (other.getFoodLevel() * 100) / 20;
        menuItem.setLore("&7Health: &f" + healthPercentage + "%");
        menuItem.setLore("&7Food: &f" + foodPercentage + "%");
        menuItem.setLore(" ");
        menuItem.setLore("&7Left-click to spectate!");

        menuItem.setAction((item, clicker, clickedBlock, clickType) -> clicker.teleport(other));
        return menuItem;
    }
}
