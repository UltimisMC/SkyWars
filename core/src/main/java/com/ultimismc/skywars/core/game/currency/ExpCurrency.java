package com.ultimismc.skywars.core.game.currency;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@Getter
public class ExpCurrency implements Currency {

    private final String name = "exp";
    private final ChatColor currencyColor = ChatColor.LIGHT_PURPLE;

    @Override
    public void increaseCurrency(User user, int amount) {
        UserStatistics userStatistics = user.getStatistics();
        userStatistics.increaseExp(amount);
    }

    @Override
    public void decreaseCurrency(User user, int amount) {
        throw new UnsupportedOperationException("Exp cannot be decreased.");
    }

    @Override
    public String getDisplayAmount(int price) {
        return Currency.super.getDisplayAmount(price) + " SkyWars Experience";
    }

    @Override
    public boolean canAfford(User user, int totalExp) {
        UserStatistics userStatistics = user.getStatistics();
        return userStatistics.getTotalExp() >= totalExp;
    }
}
