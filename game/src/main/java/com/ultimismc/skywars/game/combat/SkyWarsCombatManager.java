package com.ultimismc.skywars.game.combat;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.combat.CombatAdapter;
import xyz.directplan.directlib.combat.CombatManager;

/**
 * @author DirectPlan
 */
public class SkyWarsCombatManager extends CombatManager<User> {

    private final UserManager userManager;

    public SkyWarsCombatManager(SkyWarsPlugin plugin, CombatAdapter<User> combatAdapter) {
        super(plugin, combatAdapter);
        userManager = plugin.getUserManager();
    }

    @Override
    public User getUser(Player player) {
        return userManager.getCachedUser(player);
    }
}
