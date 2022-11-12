package com.ultimismc.skywars.core.game.currency;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@Getter
public class SoulCurrency implements Currency {

    private final String name = "soul";
    private final ChatColor currencyColor = ChatColor.AQUA;

    @Override
    public void increaseCurrency(User user, int amount) {
        UserStatistics userStatistics = user.getStatistics();
        userStatistics.increaseSouls(amount);
    }

    @Override
    public void decreaseCurrency(User user, int amount) {
        UserStatistics userStatistics = user.getStatistics();
        userStatistics.decreaseSouls(amount);
    }

    @Override
    public boolean canAfford(User user, int amount) {
        UserStatistics userStatistics = user.getStatistics();
        return userStatistics.getSouls() >= amount;
    }

    @Override
    public String getDisplayAmount(int price) {
        return Currency.super.getDisplayAmount(price) + " Soul" + (price > 1 ? "s" : "");
    }
}
