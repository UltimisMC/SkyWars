package com.ultimismc.skywars.lobby.user;

import com.ultimismc.skywars.lobby.LobbyManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class UserListener implements Listener {

    private final LobbyManager lobbyManager;
    private final UserManager userManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        userManager.loadUserAsync(player.getUniqueId(), true, user -> {
            user.setPlayer(player);
            user.setOnline(true);
            lobbyManager.handleJoin(user);
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        userManager.saveUserAsync(player.getUniqueId(), true, user -> {
            user.setOnline(false);
            lobbyManager.handleQuit(user);
        });
    }
}
