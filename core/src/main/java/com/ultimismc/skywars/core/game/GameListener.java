package com.ultimismc.skywars.core.game;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class GameListener implements Listener {

    private final UserManager userManager;
    private final FeatureHandler featureHandler;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

    }
}
