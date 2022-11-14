package com.ultimismc.skywars.game;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.map.Island;
import com.ultimismc.skywars.core.game.map.Map;
import com.ultimismc.skywars.game.config.GameConfigKeys;
import com.ultimismc.skywars.game.config.MapConfigKeys;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import xyz.directplan.directlib.CustomLocation;

import java.util.List;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class GameServerInitializer {

    private final SkyWarsPlugin plugin;

    @Getter private GameServer gameServer;

    public void initializeServer() {

        GameInfo gameInfo = loadGameInfo();
        GameType gameType = gameInfo.getGameType();
        TeamType teamType = gameInfo.getTeamType();

        String serverId = gameInfo.getServerId();

        GameMap gameMap = loadGameMap();

        gameServer = new GameServer(serverId, gameType, teamType, gameMap.toMap());

        if(gameServer.isSetupMode()) {
            plugin.log("Server is on Setup Mode. ");
            return;
        }
        plugin.log("Checking redis connection...");
        // Check if redis is established
        plugin.log("Sending startup payload for server " + gameInfo.getServerId() + "...");
        // Sending message to lobby about this server...
    }

    public void finalizeServer() {
        plugin.log("Sending shutdown payload for server " + gameServer.getServerId() + "...");
        // Send shutdown message to lobby.

        plugin.log("Saving configuration files...");
    }

    private GameInfo loadGameInfo() {
        String serverId = GameConfigKeys.SERVER_ID.getStringValue();
        String gameTypeString = GameConfigKeys.GAME_TYPE.getStringValue();
        String teamTypeString = GameConfigKeys.GAME_TEAM_TYPE.getStringValue();

        GameType gameType = GameType.valueOf(gameTypeString);
        TeamType teamType = TeamType.valueOf(teamTypeString);

        return new GameInfo(serverId, gameType, teamType);
    }

    private GameMap loadGameMap() {

        String name = MapConfigKeys.MAP_NAME.getStringValue();
        List<String> serializedIslands = MapConfigKeys.MAP_SERIALIZED_ISLANDS.getStringList();
//        List<String> serializedChests = MapConfigKeys.MAP_SERIALIZED_CHESTS.getStringList();

        GameMap gameMap = new GameMap(name);


        for(String serializedIsland : serializedIslands) {

            if(serializedIsland.isEmpty()) continue;
            String[] args = serializedIsland.split("/");
            String serializedLocation = args[0];
            gameMap.addIsland(serializedLocation);
        }
        return gameMap;
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

    private static class GameMap {

        private final Map map;

        public GameMap(String name) {
            map = new Map(name);
        }

        public void addIsland(Island island) {
            map.addIsland(island);
        }

        public void addIsland(String serializedCageLocation) {
            CustomLocation customLocation = CustomLocation.stringToLocation(serializedCageLocation);
            Location cageLocation = customLocation.toBukkitLocation();
            addIsland(new Island(cageLocation));
        }

        public Map toMap() {
            return map;
        }
    }
}
