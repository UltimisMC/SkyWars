package com.ultimismc.skywars.game.user;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public class UserRewardSummary {

    private final Map<Currency, Integer> earnedStatistics = new HashMap<>();

    public void addCurrencyStat(User user, Currency currency, int amount, String reason, boolean silent) {
        if(amount <= 0) return;
        if(silent) {
            currency.increaseCurrency(user, amount);
        }else {
            currency.increaseCurrencyReason(user, amount, reason);
        }

        Integer stat = earnedStatistics.get(currency);
        if(stat == null) {
            stat = 0;
        }
        earnedStatistics.put(currency, stat + amount);
    }
}
