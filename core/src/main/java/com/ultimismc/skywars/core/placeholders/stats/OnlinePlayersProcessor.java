package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class OnlinePlayersProcessor implements UserPlaceholderProcessor {

    private final SkyWarsPlugin plugin;
    private final TeamType teamType;

    @Override
    public String process(User user, String value) {
        SkyWarsServerManager serverManager = plugin.getServerManager();
        int onlinePlayers = serverManager.getOnlinePlayers(teamType, null);
        return String.valueOf(onlinePlayers);
    }
}
