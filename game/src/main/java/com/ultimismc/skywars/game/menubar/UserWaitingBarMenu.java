package com.ultimismc.skywars.game.menubar;

import com.ultimismc.skywars.core.user.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
public class UserWaitingBarMenu extends GameBarMenu {

    public UserWaitingBarMenu(User user) {
        super(user);
    }

    @Override
    public void build(Player player) {
        super.build(player);
        addBarItem(0, Material.BOW, "&aKit Selector");
    }
}
