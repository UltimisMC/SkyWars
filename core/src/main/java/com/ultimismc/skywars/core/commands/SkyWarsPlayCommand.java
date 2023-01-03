package com.ultimismc.skywars.core.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Syntax;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.menu.GameMenuHandler;
import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
@CommandAlias("playskywars|playsw")
public class SkyWarsPlayCommand extends BaseCommand {

    @Dependency
    private GameMenuHandler gameMenuHandler;

    @Default
    @Syntax("<team>")
    public void onMapSelector(User user, TeamType teamType) {
        gameMenuHandler.openSkyWarsPlayMenu(user, teamType);
    }
}
