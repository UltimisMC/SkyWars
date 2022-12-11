package com.ultimismc.skywars.game;

import com.mysql.jdbc.ServerPreparedStatement;
import com.ultimismc.skywars.game.chest.Chest;
import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
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

        event.setCancelled(true);
        ChestHandler chestHandler = gameHandler.getChestHandler();
        chestHandler.openChest(userGameSession, clickedBlock);
        PluginUtility.playSound(player, Sound.CHEST_OPEN);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(!gameHandler.hasStarted()) {
            event.setCancelled(true);
            return;
        }
        Player player = event.getPlayer();
        UserGameSession userGameSession = gameHandler.getSession(player);
        if(userGameSession.isSetupMode()) return;
        if(!userGameSession.isSpectator()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!gameHandler.hasStarted()) {
            event.setCancelled(true);
            return;
        }
        Player player = event.getPlayer();
        UserGameSession userGameSession = gameHandler.getSession(player);
        if(userGameSession.isSetupMode()) return;
        if(!userGameSession.isSpectator()) return;

        event.setBuild(false);
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        UserGameSession userGameSession = gameHandler.getSession(player);
        if(userGameSession.isSpectator()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        UserGameSession userGameSession = gameHandler.getSession(player);
        if(userGameSession.isSpectator()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        if(entity instanceof ArmorStand) {
            event.setCancelled(true);
        }
    }
}
