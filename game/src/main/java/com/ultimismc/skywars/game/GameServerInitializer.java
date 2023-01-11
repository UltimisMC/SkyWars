package com.ultimismc.skywars.game;

import com.ultimismc.skywars.core.ServerInitializer;
import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.Map;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.game.config.GameConfigKeys;
import com.ultimismc.skywars.game.config.MapConfigKeys;
import lombok.Data;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 * @author DirectPlan
 */
public class GameServerInitializer implements ServerInitializer  {

    @Getter private World gameWorld;

    @Getter private GameConfig gameConfig;

    @Override
    public GameConfig loadGameConfig(SkyWarsPlugin plugin) {
        String worldName = ConfigKeys.WORLD_NAME.getStringValue();
        gameWorld = Bukkit.getWorld(worldName);
        if(gameWorld == null) {
            plugin.disablePlugin("World '" + worldName + "' does not exist. Shutting down");
            return null;
        }

        GameInfo gameInfo = loadGameInfo();
        GameType gameType = gameInfo.getGameType();
        TeamType teamType = gameInfo.getTeamType();

        String serverId = gameInfo.getServerId();

        String name = MapConfigKeys.MAP_NAME.getStringValue();

        gameConfig = new GameConfig(serverId, gameType, teamType, gameWorld, new Map(name), false);

        if(gameConfig.isSetupMode()) {
            plugin.log("Server is on Setup Mode. ");
        }
        return gameConfig;
    }

    @Override
    public void saveGameConfig(SkyWarsPlugin plugin) {
        plugin.log("Saving game map...");
        Map map = gameConfig.getMap();
        MapConfigKeys.MAP_NAME.setValue(map.getName());
    }

    private GameInfo loadGameInfo() {
        String serverId = GameConfigKeys.SERVER_ID.getStringValue();
        String gameTypeString = GameConfigKeys.GAME_TYPE.getStringValue();
        String teamTypeString = GameConfigKeys.GAME_TEAM_TYPE.getStringValue();

        GameType gameType = GameType.valueOf(gameTypeString);
        TeamType teamType = TeamType.valueOf(teamTypeString);

        return new GameInfo(serverId, gameType, teamType);
    }

    @Data
    private static class GameInfo {

        private final String id;
        private final GameType gameType;
        private final TeamType teamType;

        public String getServerId() {
            char gameChar = gameType.name().charAt(0);
            char teamChar = teamType.name().charAt(0);
            return id + teamChar + gameChar;
        }
    }
}
