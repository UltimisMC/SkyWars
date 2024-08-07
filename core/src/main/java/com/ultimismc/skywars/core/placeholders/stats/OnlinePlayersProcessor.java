package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
public class OnlinePlayersProcessor extends UserPlaceholderProcessor {

    private final SkyWarsPlugin plugin;
    private final TeamType teamType;

    public OnlinePlayersProcessor(UserManager userManager, SkyWarsPlugin plugin, TeamType teamType) {
        super(userManager);

        this.plugin = plugin;
        this.teamType = teamType;
    }

    @Override
    public String process(User user, String value) {
        SkyWarsServerManager serverManager = plugin.getServerManager();
        int onlinePlayers = serverManager.getOnlinePlayers(teamType, null);
        return String.valueOf(onlinePlayers);
    }
}
