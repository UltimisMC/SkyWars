package com.ultimismc.skywars.game.user;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameStatistics;
import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
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
    private final GameConfig gameConfig;

    private boolean spectator;
    private boolean setupMode;
    private Island currentIsland;
    private GameTeam gameTeam;
    private final UserRewardSummary rewardSummary = new UserRewardSummary();

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

    public boolean hasExceededMaximumSouls() {
        UserStatistics userStatistics = user.getStatistics();
        return userStatistics.getSouls() >= userStatistics.getMaximumSouls();
    }

    public void teleport(Location location) {
        user.teleport(location);
    }

    public void teleportToIsland() {
        if(currentIsland == null) return;
        Location location = currentIsland.getCageLocation();
        teleport(location);
    }

    public void addCurrencyStat(Currency currency, int amount, String reason, boolean silent) {
        rewardSummary.addCurrencyStat(user, currency, amount, reason, silent);
    }

    public void addCurrencyStat(Currency currency, int amount, String reason) {
        addCurrencyStat(currency, amount, reason, false);
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

    public boolean isOnline() {
        return user.isOnline();
    }

    public void increaseKill() {
        gameStatistics.increaseKill();
    }

    public void increaseWin() {
        gameStatistics.increaseWin();
    }

    public void increaseAssists() {
        gameStatistics.increaseAssists();
    }

    public void increaseChestsOpened() {
        gameStatistics.increaseChestsOpened();
    }

    @Override
    public String toString() {
        return getName();
    }
}
