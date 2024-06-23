package com.ultimismc.skywars.game.menubar;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.menubar.menu.KitSelectorMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
public class UserWaitingBarLayout extends GameBarLayout {

    public UserWaitingBarLayout(SkyWarsPlugin plugin, GameHandler gameHandler, User user) {
        super(plugin, gameHandler, user);
    }

    @Override
    public void build(Player player) {
        super.build(player);
        addBarItem(0, Material.BOW, "&aKit Selector", new KitSelectorMenu(gameHandler, user));
    }
}
