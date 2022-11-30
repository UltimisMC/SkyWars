package com.ultimismc.skywars.game;

import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
        Player player = event.getPlayer();
        UserGameSession userGameSession = gameHandler.getSession(player);
        if(userGameSession.isSpectator()) {
            event.setCancelled(true);
            return;
        }
        Block clickedBlock = event.getClickedBlock();
        Action action = event.getAction();
        if(clickedBlock == null) return;
        if(action != Action.RIGHT_CLICK_BLOCK) return;
        if(clickedBlock.getType() != Material.CHEST) return;

        ChestHandler chestHandler = gameHandler.getChestHandler();
        chestHandler.openChest(userGameSession, clickedBlock);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(gameHandler.hasStarted()) return;

        Player player = event.getPlayer();
        UserGameSession userGameSession = gameHandler.getSession(player);
        if(userGameSession.isSetupMode()) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(gameHandler.hasStarted()) return;

        Player player = event.getPlayer();
        UserGameSession userGameSession = gameHandler.getSession(player);
        if(userGameSession.isSetupMode()) return;

        event.setBuild(false);
        event.setCancelled(true);
    }
}
