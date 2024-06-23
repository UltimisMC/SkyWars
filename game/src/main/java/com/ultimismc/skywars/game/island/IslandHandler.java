package com.ultimismc.skywars.game.island;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.Cage;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.CageHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.config.MapConfigKeys;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import xyz.directplan.directlib.CustomLocation;

import java.util.*;

/**
 * @author DirectPlan
 */
@Getter
public class IslandHandler implements FeatureInitializer {

    @Getter private final String name = "Island Handler";

    private final GameHandler gameHandler;
    private final CageHandler cageHandler;

    private final Set<Island> islands = new HashSet<>();
    private final Cage defaultCage;

    public IslandHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        CosmeticManager cosmeticManager = gameHandler.getCosmeticManager();
        cageHandler = cosmeticManager.getCageHandler();

        defaultCage = cageHandler.getDefaultCage();

    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        List<String> serializedIslands = MapConfigKeys.MAP_SERIALIZED_ISLANDS.getStringList();
        TeamType teamType = gameHandler.getTeamType();

        World gameWorld = gameHandler.getGameWorld();

        for(String serializedIsland : serializedIslands) {
            if(serializedIsland.isEmpty()) continue;

            CustomLocation customLocation = CustomLocation.stringToLocation(serializedIsland);
            Location cageLocation = customLocation.toBukkitLocation();
            cageLocation.setWorld(gameWorld);

            Island island = new Island();
            island.setCageLocation(cageLocation);

            addIsland(teamType, island);
        }
    }

    @Override
    public void shutdownFeature(SkyWarsPlugin plugin) {
        List<String> serializedIslands = new ArrayList<>();

        for(Island island : islands) {
            Location cageLocation = island.getCageLocation();
            String serializedCageLocation = CustomLocation.locationToString(cageLocation);
            serializedIslands.add(serializedCageLocation);
        }

        MapConfigKeys.MAP_SERIALIZED_ISLANDS.setValue(serializedIslands);

        removeAllCages();
    }

    public Island getAvailableIsland() {
        Optional<Island> optionalIsland = islands.stream().filter(island -> !island.isTaken()).findFirst();
        return optionalIsland.orElse(null);
    }

    public void handleIslandJoin(UserGameSession userGameSession) {

        Island island = getAvailableIsland();
        if(island == null) {
            throw new RuntimeException("There is no available island. Current: " + getSize() + " " + TeamType.SOLO.getMaximumPlayers());
        }
        Location cageLocation = island.getCageLocation();
        island.setTaken(true);
        userGameSession.setCurrentIsland(island);
        // Get user cage

        User user = userGameSession.getUser();
        Cage selectedCage = user.getSetting(Cage.class, cageHandler.getSettingKey());
        if(selectedCage != null && selectedCage != defaultCage) {
            island.setCage(selectedCage);
            placeCage(selectedCage, cageLocation, false);
        }
        if(selectedCage != null) {
            user.sendMessage("&aA cage was detected: " + selectedCage.getName());
        }

        cageLocation = cageLocation.clone().add(0, 1, 0);
        userGameSession.teleport(cageLocation);
    }

    public void handleIslandQuit(UserGameSession userGameSession) {
        if(gameHandler.hasStarted()) return;
        Island currentIsland = userGameSession.getCurrentIsland();
        if(currentIsland == null) {
            throw new RuntimeException("No island was found for " + userGameSession.getName());
        }
        currentIsland.setTaken(false);

        // Reset island.
        restoreIsland(currentIsland);
    }

    public void restoreIsland(Island island) {
        Cage cage = island.getCage();
        if(cage == null) return;
        Location cageLocation = island.getCageLocation();
        placeCage(defaultCage, cageLocation, false);
    }

    public void removeAllCages() {
        for(Island island : islands) {
            Location location = island.getCageLocation();
            location = location.clone().add(0, -2, 0);
            cageHandler.removeCage(location);
        }
    }

    public void addIsland(TeamType teamType, Island island) {
        island.setCage(defaultCage);
        Location cageLocation = island.getCageLocation();
        islands.add(island);
        if(teamType != null) {
            cageHandler.placeCage(teamType, defaultCage, cageLocation, false);
            return;
        }
        placeCage(defaultCage, cageLocation, false);
    }

    public void addIsland(Island island) {
        addIsland(null, island);
    }

    public void removeIsland(Island island) {
        islands.remove(island);
    }

    public int getSize() {
        return islands.size();
    }

    public void placeCage(Cage cage, Location location, boolean ignoreAir) {
        TeamType teamType = gameHandler.getTeamType();
        cageHandler.placeCage(teamType, cage, location, ignoreAir);
    }
}
