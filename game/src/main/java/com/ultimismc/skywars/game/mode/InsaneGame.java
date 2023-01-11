package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.game.handler.Game;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
public class InsaneGame extends Game {

    public InsaneGame(SkyWarsPlugin plugin, GameHandler gameHandler) {
        super(plugin, gameHandler, new InsaneChestRegistry());
    }

    @Override
    public void prepareUser(UserGameSession user) {
        super.prepareUser(user);

    }

    @Override
    public void startGame() {
        super.startGame();

        gameHandler.broadcastFunction(user -> {
            Player player = user.getPlayer();
            PluginUtility.sendTitle(player, 20, 40, 20, "&c&lINSANE MODE");
        });
    }
}
