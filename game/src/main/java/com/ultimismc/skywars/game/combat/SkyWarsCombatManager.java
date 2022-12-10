package com.ultimismc.skywars.game.combat;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.combat.CombatAdapter;
import xyz.directplan.directlib.combat.CombatManager;

/**
 * @author DirectPlan
 */
public class SkyWarsCombatManager extends CombatManager<UserGameSession> {

    private final GameHandler gameHandler;

    public SkyWarsCombatManager(SkyWarsPlugin plugin, GameHandler gameHandler, CombatAdapter<UserGameSession> combatAdapter) {
        super(plugin, combatAdapter);
        this.gameHandler = gameHandler;
    }

    @Override
    public UserGameSession getUser(Player player) {
        return gameHandler.getSession(player);
    }
}
