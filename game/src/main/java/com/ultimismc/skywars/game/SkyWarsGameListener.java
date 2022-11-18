package com.ultimismc.skywars.game;

import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class SkyWarsGameListener implements Listener {

    private final GameHandler gameHandler;

    @EventHandler
    public void onPreJoin(AsyncPlayerPreLoginEvent event) {
        if(gameHandler.isOpen()) return;

        event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST);
        event.setKickMessage(PluginUtility.translateMessage("&cGame is full or has already started!"));
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        Action action = event.getAction();
        if(clickedBlock == null || action != Action.RIGHT_CLICK_BLOCK) return;
        if(clickedBlock.getType() != Material.CHEST) return;

        ChestHandler chestHandler = gameHandler.getChestHandler();
        chestHandler.openChest(clickedBlock);
    }
}
