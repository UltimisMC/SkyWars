package com.ultimismc.skywars.game.menubar;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.menubar.menu.KitSelectorMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
public class UserWaitingBarMenu extends GameBarMenu {

    public UserWaitingBarMenu(GameHandler gameHandler, User user) {
        super(gameHandler, user);
    }

    @Override
    public void build(Player player) {
        super.build(player);
        addBarItem(0, Material.BOW, "&aKit Selector", new KitSelectorMenu(gameHandler, user));
    }
}
