package com.ultimismc.skywars.game;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.Map;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.game.chest.Chest;
import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.config.GameConfigKeys;
import com.ultimismc.skywars.game.config.MapConfigKeys;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.island.Island;
import com.ultimismc.skywars.game.island.IslandHandler;
import lombok.Data;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import xyz.directplan.directlib.CustomLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class GameServerInitializer {

    private final SkyWarsPlugin plugin;

    private final IslandHandler islandHandler;
    private final ChestHandler chestHandler;

    public GameServerInitializer(SkyWarsPlugin plugin, GameHandler gameHandler) {
        this.plugin = plugin;
        this.islandHandler = gameHandler.getIslandHandler();
        this.chestHandler = gameHandler.getChestHandler();
    }

    @Getter private GameServer gameServer;

    public void initializeServer() {

        GameInfo gameInfo = loadGameInfo();
        GameType gameType = gameInfo.getGameType();
        TeamType teamType = gameInfo.getTeamType();

        String serverId = gameInfo.getServerId();

        GameMap gameMap = loadGameMap(teamType);
        if(gameMap == null) {
            return;
        }
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
        plugin.log("Saving game map...");
        saveGameMap();

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

    private GameMap loadGameMap(TeamType teamType) {

        String name = MapConfigKeys.MAP_NAME.getStringValue();
        List<String> serializedIslands = MapConfigKeys.MAP_SERIALIZED_ISLANDS.getStringList();
        List<String> serializedChests = MapConfigKeys.MAP_SERIALIZED_CHESTS.getStringList();

        GameMap gameMap = new GameMap(name, teamType, islandHandler, chestHandler);


        for(String serializedIsland : serializedIslands) {

            if(serializedIsland.isEmpty()) continue;
//            String[] args = serializedIsland.split("/");
            gameMap.addIsland(serializedIsland);
        }
        String worldName = MapConfigKeys.WORLD_NAME.getStringValue();
        World world = Bukkit.getWorld(worldName);
        if(world == null) {
            plugin.shutdown("World '" + worldName + "' does not exist. Shutting down");
            return null;
        }

        for(String serializedChest : serializedChests) {
            if(serializedChest.isEmpty()) continue;
            String[] args = serializedChest.split("/");
            String serializedLocation = args[0];
            CustomLocation customLocation = CustomLocation.stringToLocation(serializedLocation);
            boolean midChest = Boolean.parseBoolean(args[1]);

            Block block = world.getBlockAt(customLocation.toBukkitLocation());
            if(block.getType() != Material.CHEST) continue;
            gameMap.addChest(block, midChest);
        }
        /*
        serialized-chests:
- -199.0, 29.0, -25.0/true
- -190.0, 32.0, -27.0/false
- -202.0, 32.0, -37.0/false
- -200.0, 29.0, -26.0/true
- -198.0, 32.0, -35.0/false
- -209.0, 32.0, -27.0/false
- -211.0, 32.0, -23.0/false
- -198.0, 29.0, -26.0/true
- -188.0, 32.0, -23.0/false
- -196.0, 32.0, -14.0/false
- -200.0, 32.0, -16.0/false
- -199.0, 29.0, -27.0/true


         */
        return gameMap;
    }

    private void saveGameMap() {

        Map map = gameServer.getMap();
        MapConfigKeys.MAP_NAME.setValue(map.getName());

        List<String> serializedChests = new ArrayList<>();
        List<String> serializedIslands = new ArrayList<>();

        for(Chest chest : chestHandler.getChests().values()) {
            Location location = chest.getLocation();
            String serializedLocation = CustomLocation.locationToString(location);
            boolean midChest = chest.isMidChest();
            serializedChests.add(serializedLocation + "/" + midChest);
        }
        for(Island island : islandHandler.getIslands().values()) {
            Location cageLocation = island.getCageLocation();
            String serializedCageLocation = CustomLocation.locationToString(cageLocation);
            serializedIslands.add(serializedCageLocation);
        }

        MapConfigKeys.MAP_SERIALIZED_CHESTS.setValue(serializedChests);
        MapConfigKeys.MAP_SERIALIZED_ISLANDS.setValue(serializedIslands);
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

        private final TeamType teamType;
        private final IslandHandler islandHandler;
        private final ChestHandler chestHandler;
        private final Map map;

        public GameMap(String mapName, TeamType teamType, IslandHandler islandHandler, ChestHandler chestHandler) {
            map = new Map(mapName);
            this.teamType = teamType;
            this.islandHandler = islandHandler;
            this.chestHandler = chestHandler;
        }

        public void addIsland(Island island) {
            islandHandler.addIsland(teamType, island);
        }

        public void addChest(Block block, boolean midChest) {
            chestHandler.addChest(block, midChest);
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
