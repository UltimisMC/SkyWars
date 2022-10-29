package com.ultimismc.skywars.lobby.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.LobbyManager;

/**
 * @author DirectPlan
 */
@CommandAlias("statistics|stats")
public class StatsCommand extends BaseCommand {

    @Dependency
    private LobbyManager lobbyManager;

    @Default
    public void onStats(User user) {
        lobbyManager.openStatsMenu(user);
    }
}
