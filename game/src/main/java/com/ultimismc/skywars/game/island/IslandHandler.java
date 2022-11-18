package com.ultimismc.skywars.game.island;

import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.Cage;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author DirectPlan
 */
@Getter
@RequiredArgsConstructor
public class IslandHandler {

    private final GameHandler gameHandler;

    private final Map<Location, Island> islands = new HashMap<>();

    public Island getAvailableIsland() {
        Optional<Island> optionalIsland = islands.values().stream().filter(island -> !island.isTaken()).findFirst();
        return optionalIsland.orElse(null);
    }

    public void handleCageJoin(UserGameSession userGameSession) {

        Island island = getAvailableIsland();
        if(island == null) {
            throw new RuntimeException("There is no available island. Current: " + getSize() + " " + TeamType.SOLO.getMaximumPlayers());
        }
        Location cageLocation = island.getCageLocation();
        userGameSession.teleport(cageLocation);
        island.setTaken(true);
        userGameSession.setCurrentIsland(island);
        // Get user cage
        // Place cage schematic
    }

    public void handleCageQuit(UserGameSession userGameSession) {
        Island currentIsland = userGameSession.getCurrentIsland();
        if(currentIsland == null) {
            throw new RuntimeException("No island was found for " + userGameSession.getName());
        }

        Cage cage = currentIsland.getCage();
        // Revert changes.
    }

    public void addIsland(Island island) {
        islands.put(island.getCageLocation(), island);
    }

    public Island getIsland(Location location) {
        return islands.get(location);
    }

    public void removeIsland(Location location) {
        islands.remove(location);
    }

    public void removeIsland(Island island) {
        removeIsland(island.getCageLocation());
    }

    public int getSize() {
        return islands.size();
    }

}
