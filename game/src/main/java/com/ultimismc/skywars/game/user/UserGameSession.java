package com.ultimismc.skywars.game.user;

import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.GameStatistics;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.team.GameTeam;
import com.ultimismc.skywars.game.island.Island;
import lombok.Data;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author DirectPlan
 */
@Data
@Setter
public class UserGameSession {

    private final User user;
    private final GameServer gameServer;

    private boolean spectator;
    private boolean setupMode;
    private Island currentIsland;
    private GameTeam gameTeam;

    private final GameStatistics gameStatistics = new GameStatistics();

    public String getName() {
        return user.getName();
    }

    public String getDisplayName() {
        return user.getDisplayName();
    }

    public int getKills() {
        return gameStatistics.getKills();
    }

    public void teleport(Location location) {
        user.teleport(location);
    }

    public void teleportToIsland() {
        if(currentIsland == null) return;
        Location location = currentIsland.getCageLocation();
        teleport(location);
    }

    public void sendMessage(String message) {
        user.sendMessage(message);
    }

    public UUID getUuid() {
        return user.getUuid();
    }

    public Player getPlayer() {
        return user.getPlayer();
    }

    public void increaseKill() {
        gameStatistics.increaseKill();
    }

    public void increaseWin() {
        gameStatistics.increaseWin();
    }

    public void increaseChestsOpened() {
        gameStatistics.increaseChestsOpened();
    }
}
