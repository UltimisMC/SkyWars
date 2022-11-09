package com.ultimismc.skywars.lobby;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LobbyListener implements Listener {

    private final LobbyManager lobbyManager;

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onSoulWellClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if(block == null) return;
        if(block.getType() != Material.ENDER_PORTAL_FRAME) return;

        Player player = event.getPlayer();
        if(player.isSneaking() && player.isOp()) return; // To allow breaking

        lobbyManager.openSoulWellMenu(player);
        event.setCancelled(true);
    }
}
