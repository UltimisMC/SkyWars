package com.ultimismc.skywars.game.chest;

import com.ultimismc.skywars.game.events.AbstractSkyWarsEvent;
import com.ultimismc.skywars.game.handler.GameHandler;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
public class ChestRefillSkyWarsEvent extends AbstractSkyWarsEvent {

    private final RefillPhase refillPhase;
    public ChestRefillSkyWarsEvent(RefillPhase refillPhase, long scheduledIn) {
        super("Refill", scheduledIn);
        this.refillPhase = refillPhase;
    }

    @Override
    public void executeEvent(GameHandler gameHandler) {
        gameHandler.log("Beginning chest refill for " + refillPhase.name() + " phase.");
        ChestHandler chestHandler = gameHandler.getChestHandler();
        chestHandler.refillAllChests(refillPhase);
        gameHandler.broadcastFunction(user -> {
            Player player = user.getPlayer();
            PluginUtility.playSound(player, Sound.CHEST_OPEN);
            PluginUtility.sendSubTitle(player, 10, 20, 10, "&eAll chests have been refilled!");
        });
    }
}
