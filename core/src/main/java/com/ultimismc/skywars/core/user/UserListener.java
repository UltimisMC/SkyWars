package com.ultimismc.skywars.core.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
@Setter
public class UserListener implements Listener {

    private UserLoadedListener userLoadedListener;
    private UserSavedListener userSavedListener;

    private final UserManager userManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        userManager.loadUserAsync(player.getUniqueId(), true, user -> {
            user.setPlayer(player);
            user.setOnline(true);
            if(userLoadedListener != null) userLoadedListener.onUserLoaded(user);
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        userManager.saveUserAsync(player.getUniqueId(), true, user -> {
            user.setOnline(false);
            if(userSavedListener != null) userSavedListener.onUserSaved(user);
        });
    }
}
