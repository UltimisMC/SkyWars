package com.ultimismc.skywars.game.events;

import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.handler.GameHandler;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
public class RefillSkyWarsEvent extends AbstractSkyWarsEvent {

    public RefillSkyWarsEvent(long scheduledIn) {
        super("Refill", scheduledIn);
    }

    @Override
    public void executeEvent(GameHandler gameHandler) {
        ChestHandler chestHandler = gameHandler.getChestHandler();
        chestHandler.refillAllChests();
        gameHandler.broadcastFunction(user -> {
            Player player = user.getPlayer();
            PluginUtility.playSound(player, Sound.CHEST_OPEN);
            PluginUtility.sendTitle(player, "&eAll chests have been refilled!");
        });
    }
}
