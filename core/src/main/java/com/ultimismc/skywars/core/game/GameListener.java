package com.ultimismc.skywars.core.game;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.Prestige;
import com.ultimismc.skywars.core.user.User;
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

        User user = userManager.getCachedUser(player);
        if(user.isScramble()) return;
        Level level = user.getLevel();
        Prestige selectedPrestigeIcon = user.getSelectedPrestigeIcon();

        String prestigePrefix = level.getDisplayName(selectedPrestigeIcon, true);


    }
}
