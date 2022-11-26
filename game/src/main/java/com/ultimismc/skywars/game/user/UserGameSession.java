package com.ultimismc.skywars.game.user;

import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.GameStatistics;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.island.Island;
import lombok.Data;
import lombok.Setter;
import org.bukkit.Location;

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

    private final GameStatistics gameStatistics = new GameStatistics();

    public String getName() {
        return user.getName();
    }

    public int getKills() {
        return gameStatistics.getKills();
    }

    public void teleport(Location location) {
        user.teleport(location);
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
