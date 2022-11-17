package com.ultimismc.skywars.game;

import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class GameListener implements Listener {

    private final GameHandler gameHandler;

    @EventHandler
    public void onPreJoin(AsyncPlayerPreLoginEvent event) {
        if(gameHandler.isOpen()) return;

        event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST);
        event.setKickMessage(PluginUtility.translateMessage("&cGame is full or has already started!"));
    }
}
