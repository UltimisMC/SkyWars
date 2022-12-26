package com.ultimismc.skywars.core.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Syntax;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

import java.util.Locale;

/**
 * @author DirectPlan
 */
@CommandAlias("play")
public class PlayCommand extends BaseCommand {

    @Dependency
    private SkyWarsServerManager serverManager;

    @Default
    @Syntax("<mode>")
    public void onPlayCommand(User user, String mode) {
        GameMode gameMode;
        try {
            gameMode = GameMode.valueOf(mode.toUpperCase(Locale.ROOT));
        }catch (Exception e) {
            user.sendMessage("&aRight click with the compass in a lobby to select a game!");
            return;
        }
        serverManager.sendToAvailableServer(user, gameMode.getTeamType(), gameMode.getGameType());
    }

    @Getter
    enum GameMode {

        SOLO_NORMAL(TeamType.SOLO, GameType.NORMAL),
        SOLO_INSANE(TeamType.SOLO, GameType.INSANE),
        TEAMS_NORMAL(TeamType.DOUBLES, GameType.NORMAL),
        TEAMS_INSANE(TeamType.DOUBLES, GameType.INSANE);

        private final TeamType teamType;
        private final GameType gameType;

        GameMode(TeamType teamType, GameType gameType) {
            this.teamType = teamType;
            this.gameType = gameType;
        }
    }
}
